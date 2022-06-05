
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jsoup.Jsoup;

public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_1 = new javax.swing.JTextField();
        txt_2 = new javax.swing.JTextField();
        txt_3 = new javax.swing.JTextField();
        txt_4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(102, 102, 102));
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel"));
        panel.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Statistic_1");

        jLabel2.setText("Statistic_2");

        jLabel3.setText("Statistic_3");

        jLabel4.setText("Statistik_4");

        jButton1.setText("CategoryChart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("PieChart");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("QueryChart");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Weather");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3)))))
                        .addGap(0, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int s1 = Integer.valueOf(txt_1.getText());
        int s2 = Integer.parseInt(txt_2.getText());
        int s3 = Integer.parseInt(txt_3.getText());
        int s4 = Integer.parseInt(txt_4.getText());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(s1, "1", "");
        dataset.setValue(s2, "2", "");
        dataset.setValue(s3, "3", "");
        dataset.setValue(s4, "4", "");
        dataset.setValue(s1, "5", "");
        dataset.setValue(s2, "6", "");
        dataset.setValue(s3, "7", "");
        dataset.setValue(s4, "8", "");
        dataset.setValue(s1, "9", "");
        dataset.setValue(s2, "10", "");
        dataset.setValue(s3, "11", "");
        dataset.setValue(s4, "12", "");
        JFreeChart chart = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);

//        CategoryPlot catPlot = chart.getCategoryPlot();
//        catPlot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int s1 = Integer.valueOf(txt_1.getText());
        int s2 = Integer.parseInt(txt_2.getText());
        int s3 = Integer.parseInt(txt_3.getText());
        int s4 = Integer.parseInt(txt_4.getText());

        DefaultPieDataset pieData = new DefaultPieDataset();

        pieData.setValue("One", s1);
        pieData.setValue("Two", s2);
        pieData.setValue("Three", s3);
        pieData.setValue("Four", s4);

        JFreeChart chart = ChartFactory.createPieChart("Pie Chart", pieData, false, false, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        DB db = new DB();
//        try {
//            String query = "select ay, deger, deger2 from ChartDeneme ";
//            JDBCCategoryDataset dataset = new JDBCCategoryDataset(db.connConnect(), query);
//
//            JFreeChart chart = ChartFactory.createLineChart("QueryChart", "date", "deger", dataset, PlotOrientation.VERTICAL, true, true, true);
//
//            ChartPanel chartPanel = new ChartPanel(chart);
//            panel.removeAll();
//            panel.add(chartPanel, BorderLayout.CENTER);
//            panel.validate();
//        } catch (Exception e) {
//        }finally{
//            db.closeDB();
//        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        DB db = new DB();
        try {
            ArrayList<String> aylar = new ArrayList<>();
            ArrayList<String> degerler = new ArrayList<>();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            String query = "select ay, deger from ChartDeneme ";

            ResultSet rs = db.connectDB().executeQuery(query);

            while (rs.next()) {                
                aylar.add(rs.getString("ay"));
                degerler.add(rs.getString("deger"));
            }
            
            System.out.println(aylar);
            System.out.println(degerler);

            for (int i = 0; i < aylar.size(); i++) {
                for (int j = i+1; j < aylar.size(); j++) {
                    if (aylar.get(i).equals(aylar.get(j))) {
                        aylar.remove(j);
                        Integer toplam =  Integer.valueOf(degerler.get(i)) + Integer.valueOf(degerler.get(j));
                        degerler.set(i, toplam.toString() );
                        degerler.remove(j);
                    }
                }
            }
            
            System.out.println(aylar);
            System.out.println(degerler);
            
            for (int i = 0; i < aylar.size(); i++) {
                dataset.setValue(Integer.valueOf(degerler.get(i)), "data1", aylar.get(i));
            }
            
            JFreeChart chart = ChartFactory.createLineChart("Wind Speed Chart", "date", "deger", dataset, PlotOrientation.VERTICAL, true, true, true);

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();
        } catch (Exception e) {
            System.err.println("hata:" + e);
        }finally{
            db.closeDB();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //5gunluk//http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=61d2bb72068a55593de62b7ff5e41b06&units=metric
        //61d2bb72068a55593de62b7ff5e41b06
        //gunluk//http://api.openweathermap.org/data/2.5/weather?id=6058560&appid=61d2bb72068a55593de62b7ff5e41b06&units=metric
        //http://openweathermap.org/img/w/10d.png   //icon
        //https://openweathermap.org/weather-conditions  //icon list
        
        String url = "http://api.openweathermap.org/data/2.5/forecast";
        
        String id = "524901";
        String appid = "61d2bb72068a55593de62b7ff5e41b06";
        String units = "metric";

        HashMap<String, String> hm = new HashMap<>();
        hm.put("id", id);
        hm.put("appid", appid);
        hm.put("units", units);
        
        ArrayList<Double> ls = new ArrayList<>();
        ArrayList<String> dt = new ArrayList<>();
        try {
            String data = Jsoup.connect(url).timeout(30000).ignoreContentType(true).data(hm).get().body().text();
            JsonObject obj = (JsonObject) new JsonParser().parse(data);
             String asd = obj.get("cod").getAsString();
             System.out.println(asd);
            for (int i = 0; i < 9; i++) {
                Double arry = (Double) obj.getAsJsonArray("list").get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
                String date = obj.getAsJsonArray("list").get(i).getAsJsonObject().get("dt_txt").getAsString();
                String[] splt = date.split(" ");
                String[] spltTime = splt[1].split(":");
                
                dt.add(spltTime[0]+":"+spltTime[1]);
                ls.add(arry);
            }
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < ls.size(); i++) {
                dataset.setValue(ls.get(i), "data1", dt.get(i));
            }
            
            JFreeChart chart = ChartFactory.createLineChart("QueryChart", "", "°C", dataset, PlotOrientation.VERTICAL, false, false, false);

            ChartPanel chartPanel = new ChartPanel(chart);
            panel.removeAll();
            panel.add(chartPanel, BorderLayout.CENTER);
            panel.validate();
            
        } catch (Exception e) {
            System.err.println("Hata:" + e );
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txt_1;
    private javax.swing.JTextField txt_2;
    private javax.swing.JTextField txt_3;
    private javax.swing.JTextField txt_4;
    // End of variables declaration//GEN-END:variables
}
