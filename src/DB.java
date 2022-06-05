
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


public class DB {

    private String url = "org.sqlite.JDBC";//kutuphane yolu
    private String db = "jdbc:sqlite:db/veritabani.db";//sqlite browserdaki db
    
    Connection conn = null;
    Statement st = null;
    
    public Statement connectDB(){
        
        try {
            Class.forName(url);//kutuphane içindeki sınıfı çalıştırır
            conn = DriverManager.getConnection(db);
            st = conn.createStatement();
            System.out.println("Bağlantı başarılı.");
        } catch (Exception e) {
            System.err.println("Bağlantı hatası." + e);
        }
        
        
        
        return st;
    }
    
    //kapatma methodu
    
    public void closeDB(){
        try {
            if(conn != null || !conn.isClosed()){
                st.close();
                conn.close();
                System.out.println("DB kapatıldı.");
                
        }
        } catch (Exception e) {
            System.err.println("Kapatma Hatası: " + e);
        }
    }
}
