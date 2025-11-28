package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Koneksi {
    private static Connection koneksi;

    public static Connection getConnection() {
        try {
            if (koneksi == null || koneksi.isClosed()) { 
                try {
                    String url = "jdbc:mysql://localhost:3306/tr-pbo-dosen"; 
                    String user = "root"; 
                    String password = ""; 
                    
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    
                    koneksi = DriverManager.getConnection(url, user, password);
                    System.out.println("Koneksi Berhasil dibuat baru!");
                    
                } catch (SQLException t) {
                    System.out.println("Error membuat koneksi: " + t.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return koneksi;
    }
}