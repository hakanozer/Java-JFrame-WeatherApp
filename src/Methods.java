
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jsoup.Jsoup;

public class Methods {

    List<City> ls = new ArrayList<>();
    String id = SearchFrame.id;

    public JsonObject getjJsonObjForChart() {

        String url = "http://api.openweathermap.org/data/2.5/forecast";
        String appid = "464a9b37252d685486b87415e3f096bd";
        String units = "metric";

        HashMap<String, String> hm = new HashMap<>();
        hm.put("id", id);
        hm.put("appid", appid);
        hm.put("units", units);
        JsonObject obj = null;
        try {
            String data = Jsoup.connect(url).timeout(30000).ignoreContentType(true).data(hm).get().body().text();
            obj = (JsonObject) new JsonParser().parse(data);
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("json object exception: " + e);
        }

        return obj;

    }

    public JsonObject getJsonObjForInformation() {
        //http://api.openweathermap.org/data/2.5/weather?id=6058560&appid=61d2bb72068a55593de62b7ff5e41b06&units=metric
        String url = "http://api.openweathermap.org/data/2.5/weather";
        String appid = "464a9b37252d685486b87415e3f096bd";
        String units = "metric";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("id", id);
        hm.put("appid", appid);
        hm.put("units", units);
        JsonObject obj = null;
        try {
            String data = Jsoup.connect(url).timeout(30000).ignoreContentType(true).data(hm).get().body().text();
            obj = (JsonObject) new JsonParser().parse(data);
        } catch (Exception e) {

        }
        return obj;
    }

    public void searchResult(String data, JLabel lblWarning) {
        ls.clear();
        String query = "SELECT * FROM cities WHERE cname LIKE '%" + data + "%'";
        DB db = new DB();
        ResultSet rs = null;
        try {
            rs = db.connectDB().executeQuery(query);
            if (!rs.next()) {
                lblWarning.setText("Not found.");
            } else {
                lblWarning.setText("");
                rs = db.connectDB().executeQuery(query);
                while (rs.next()) {
                    City c = new City();
                    c.setCityId(rs.getString("cid"));
                    c.setCityName(rs.getString("cname"));
                    ls.add(c);

                }
            }

        } catch (SQLException e) {
            System.err.println("Select Exception: " + e);
        } finally {
            db.closeDB();
        }

    }

    public DefaultTableModel tableFill(String data, JLabel lblWarning) {
        searchResult(data, lblWarning);
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        table.addColumn("City Id");
        table.addColumn("City Name");

        for (City l : ls) {
            table.addRow(new String[]{l.getCityId(), l.getCityName()});
        }

        return table;
    }

    public void fillUpChart(JPanel panel) {

        ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> dt = new ArrayList<>();

        try {

            JsonObject obj = getjJsonObjForChart();
            for (int i = 0; i < 9; i++) {

                Double arry = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
                String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();

                String[] splt = date.split(" ");
                String[] spltTime = splt[1].split(":");

                if (i == 8) {
                    dt.add(spltTime[0] + ":" + spltTime[1] + " ");
                } else {
                    dt.add(spltTime[0] + ":" + spltTime[1]);
                }

                list.add(arry);
            }
            System.out.println(list);
            System.out.println(dt);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < dt.size(); i++) {
                dataset.setValue(list.get(i), "Temperature", dt.get(i));
            }

            JFreeChart chart = ChartFactory.createLineChart("Weather Chart", "", "°C", dataset, PlotOrientation.VERTICAL, false, true, true);
           
           

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();

        } catch (JsonSyntaxException e) {
            System.err.println("Json:" + e);
        }
    }

    public void fillUpInformationPanel(JLabel lblCityName, JLabel lblImg, JLabel lblWeather, JLabel lblWind, JLabel lblCloud, JLabel lblPressure, JLabel lblHumidity, JLabel lblCoords, JLabel lblTemprature) {

        JsonObject obj = getJsonObjForInformation();
        String cityName = obj.get("name").getAsString();
        lblCityName.setText(cityName);
        //---------------------------------------------------------------------//

        String icon = obj.getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").getAsString();
        //http://openweathermap.org/img/w/10d.png
        String urlString = "http://openweathermap.org/img/w/" + icon + ".png";

        try {
            URL url = new URL(urlString);
            BufferedImage bfImg = ImageIO.read(url);
            Image img = bfImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon imgIcon = new ImageIcon(img);
            lblImg.setIcon(imgIcon);
        } catch (Exception e) {
        }
        //---------------------------------------------------------------------//
        String weather = obj.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
        lblWeather.setText(weather);
        //---------------------------------------------------------------------//
        Double wind = (double) obj.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsDouble();
        lblWind.setText("   " + wind + " m/s");
        //---------------------------------------------------------------------//
        Double cloud = (double) obj.getAsJsonObject().get("clouds").getAsJsonObject().get("all").getAsDouble();
        lblCloud.setText(cloud + " %");
        //---------------------------------------------------------------------//
        Double pressure = (double) obj.getAsJsonObject().get("main").getAsJsonObject().get("pressure").getAsDouble();
        lblPressure.setText("  " + pressure + " hpa");
        //---------------------------------------------------------------------//
        Double humidity = (double) obj.getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsDouble();
        lblHumidity.setText(" " + humidity + " %");
        //---------------------------------------------------------------------//
        Double lon = (double) obj.getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsDouble();
        Double lat = (double) obj.getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsDouble();
        lblCoords.setText("[" + lat + "," + lon + "]");
        //---------------------------------------------------------------------//
        Double temp = (double) obj.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
        lblTemprature.setText("" + temp);
    }

    public ArrayList<Double> fillUpTemp() {
        ArrayList<Double> temps = new ArrayList<>();
        try {
            JsonObject obj = getjJsonObjForChart();
            for (int i = 0; i < 9; i++) {
                Double temp = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
                temps.add(temp);
            }

        } catch (Exception e) {
            System.err.println("Temp fill error:" + e);
        }
        return temps;
    }

    public ArrayList<String> fillUpImg() {
        ArrayList<String> ls = new ArrayList<>();
        JsonObject obj = getjJsonObjForChart();
        String img;
        for (int i = 0; i < 9; i++) {
            img = obj.getAsJsonArray("list").get(i).getAsJsonObject().getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").getAsString();
            ls.add(img);
        }

        return ls;

    }

    public void fillUpDailyChart(JPanel panel) {
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> dt = new ArrayList<>();

        try {

            JsonObject obj = getjJsonObjForChart();
            for (int i = 0; i < 33; i = i + 8) {

                Double arry = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
                String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();

                String[] splt = date.split(" ");

                dt.add(splt[0]);

                list.add(arry);
            }
            System.out.println(list);
            System.out.println(dt);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (int i = 0; i < dt.size(); i++) {
                dataset.setValue(list.get(i), "Temperature", dt.get(i));
            }

            JFreeChart chart = ChartFactory.createBarChart("Weather Chart", "", "°C", dataset, PlotOrientation.VERTICAL, false, true, false);

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();

        } catch (JsonSyntaxException e) {
            System.err.println("Json:" + e);
        }
    }

    public ArrayList<String> getHours() {

        ArrayList<String> ls = new ArrayList();
        JsonObject obj = getjJsonObjForChart();
        for (int i = 0; i < 10; i++) {
            String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();
            String[] splt = date.split(" ");
            String[] spltTime = splt[1].split(":");
            ls.add(spltTime[0] + ":" + spltTime[1]);
        }

        return ls;
    }
    
    public ArrayList<String> getWeather(){
        ArrayList<String> ls = new ArrayList<>();
        JsonObject obj = getjJsonObjForChart();
        for (int i = 0; i < 8; i++) {
            String weather = obj.getAsJsonArray("list").get(i).getAsJsonObject().getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
        ls.add(weather);
        }
        
        return ls;
    }
    
    public void fillUpHumidityChart(JPanel panel){
         ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> dt = new ArrayList<>();

        try {

            JsonObject obj = getjJsonObjForChart();
            for (int i = 0; i < 9; i++) {

                Double arry = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsDouble();
                String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();

                String[] splt = date.split(" ");
                String[] spltTime = splt[1].split(":");

                if (i == 8) {
                    dt.add(spltTime[0] + ":" + spltTime[1] + " ");
                } else {
                    dt.add(spltTime[0] + ":" + spltTime[1]);
                }

                list.add(arry);
            }
            System.out.println(list);
            System.out.println(dt);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < dt.size(); i++) {
                dataset.setValue(list.get(i), "Pressure", dt.get(i));
            }

            JFreeChart chart = ChartFactory.createLineChart("Humidity Chart", "", "%", dataset, PlotOrientation.VERTICAL, false, true, false);

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();

        } catch (JsonSyntaxException e) {
            System.err.println("Json:" + e);
        }
    }
    
    public void fillUpWindChart(JPanel panel){
         ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> dt = new ArrayList<>();

        try {

            JsonObject obj = getjJsonObjForChart();
            for (int i = 0; i < 9; i++) {

                Double arry = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsDouble();
                String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();

                String[] splt = date.split(" ");
                String[] spltTime = splt[1].split(":");

                if (i == 8) {
                    dt.add(spltTime[0] + ":" + spltTime[1] + " ");
                } else {
                    dt.add(spltTime[0] + ":" + spltTime[1]);
                }

                list.add(arry);
            }
            System.out.println(list);
            System.out.println(dt);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < dt.size(); i++) {
                dataset.setValue(list.get(i), "Pressure", dt.get(i));
            }

            JFreeChart chart = ChartFactory.createLineChart("Wind Speed Chart", "", "m/s", dataset, PlotOrientation.VERTICAL, false, true, false);

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();

        } catch (JsonSyntaxException e) {
            System.err.println("Json:" + e);
        }
    }
    
    public void fillUpCloudinessChart(JPanel panel){
          ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> dt = new ArrayList<>();

        try {

            JsonObject obj = getjJsonObjForChart();
            for (int i = 0; i < 9; i++) {

                Double arry = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("clouds").getAsJsonObject().get("all").getAsDouble();
                String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();

                String[] splt = date.split(" ");
                String[] spltTime = splt[1].split(":");

                if (i == 8) {
                    dt.add(spltTime[0] + ":" + spltTime[1] + " ");
                } else {
                    dt.add(spltTime[0] + ":" + spltTime[1]);
                }

                list.add(arry);
            }
            System.out.println(list);
            System.out.println(dt);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < dt.size(); i++) {
                dataset.setValue(list.get(i), "Pressure", dt.get(i));
            }

            JFreeChart chart = ChartFactory.createLineChart("Cloudiness Chart", "", "%", dataset, PlotOrientation.VERTICAL, false, true, false);

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();

        } catch (JsonSyntaxException e) {
            System.err.println("Json:" + e);
        }
    }
     
    
    public void fillUpMap(JPanel panel){
        
        JsonObject obj = getJsonObjForInformation();
        String lon =  obj.getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsString();
        String lat =  obj.getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsString(); 
        
        
            try {
             //String imageUrl = "http://maps.googleapis.com/maps/api/staticmap?zoom=18&size=800x800&markers="+lat+","+lon+"&zoom=5&sensor=true";
             // &markers=color:blue%7Clabel:S%7C40.702147,-74.015794  --->marker
             
             String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lon+"&markers=color:red%7Clabel:A%7C"+lat+","+lon+""+"&zoom=9&size=735x378";
             URL url = new URL(imageUrl);
             panel.add( new JLabel( new ImageIcon( new ImageIcon(url).getImage().getScaledInstance( 735, 378, java.awt.Image.SCALE_SMOOTH ) ) ) );
        } catch (MalformedURLException e) {
                System.err.println("Google Map:" + e);
        }

    }
}

