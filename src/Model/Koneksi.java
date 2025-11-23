package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    private static Connection koneksi;

    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                // Pastikan nama database sudah sesuai: tr-pbo
                String url = "jdbc:mysql://localhost:3306/tr-pbo";
                String user = "root";
                String password = "";
                
                // Memuat driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                koneksi = DriverManager.getConnection(url, user, password);
                // System.out.println("Koneksi berhasil!"); // Opsional untuk debugging
                
            } catch (ClassNotFoundException e) {
                // Jika library driver MySQL belum dimasukkan (JDBC Connector)
                JOptionPane.showMessageDialog(null, "Library MySQL Belum Dipasang!\n" + e.getMessage(), "Kesalahan Koneksi", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                // Jika XAMPP (MySQL) mati atau kredensial salah
                JOptionPane.showMessageDialog(null, "Gagal Koneksi ke Database!\nCek XAMPP Anda. \nError: " + e.getMessage(), "Kesalahan Koneksi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return koneksi;
    }
}