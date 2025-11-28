package Controller;

import Model.AkunPengguna;
import Model.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GantiPasswordController {
    
    private static final Logger logger = Logger.getLogger(GantiPasswordController.class.getName());

    public int prosesGantiPassword(AkunPengguna akun) {
        Connection conn = null;
        PreparedStatement psCek = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            if (conn == null) return -1; // Error koneksi

            // 1. Cek apakah Password Lama Benar
            String sqlCek = "SELECT * FROM pengguna_login WHERE id_user = ? AND password = ?";
            psCek = conn.prepareStatement(sqlCek);
            psCek.setString(1, akun.getIdUser());
            psCek.setString(2, akun.getPasswordLama());
            rs = psCek.executeQuery();

            if (rs.next()) {
                // 2. Update ke Password Baru
                String sqlUpdate = "UPDATE pengguna_login SET password = ? WHERE id_user = ?";
                psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setString(1, akun.getPasswordBaru());
                psUpdate.setString(2, akun.getIdUser());
                
                int result = psUpdate.executeUpdate();
                return (result > 0) ? 1 : -1; // 1 = Sukses
            } else {
                return 0; // 0 = Password Lama Salah / User tidak ada
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error controller ganti password", e);
            return -1; // -1 = Error Database
        } finally {
            try {
                if (rs != null) rs.close();
                if (psCek != null) psCek.close();
                if (psUpdate != null) psUpdate.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Gagal tutup resource", e);
            }
        }
    }
}