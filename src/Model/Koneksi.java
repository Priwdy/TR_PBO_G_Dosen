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
                String url = "jdbc:mysql://localhost:3306/tr-pbo-dosen"; // Pastikan nama DB benar
                String user = "root";
                String password = "";
                
                // MENGGUNAKAN CLASS.FORNAME (Lebih Aman)
                // Ini menggantikan baris yang error sebelumnya
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                koneksi = DriverManager.getConnection(url, user, password);
                
            } catch (ClassNotFoundException e) {
                // Error ini muncul jika Library belum dimasukkan (Solusi 1 & 2 belum dilakukan)
                JOptionPane.showMessageDialog(null, "Library MySQL Belum Dipasang!\n" + e.getMessage());
            } catch (SQLException e) {
                // Error ini muncul jika XAMPP mati atau nama DB salah
                JOptionPane.showMessageDialog(null, "Gagal Koneksi ke Database!\nCek XAMPP Anda.");
            }
        }
        return koneksi;
    }
}