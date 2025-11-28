package Controller;

import Model.Mahasiswa_UKSW;
import Model.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ControllerTambahMahasiswa {

    private static final Logger logger = Logger.getLogger(ControllerTambahMahasiswa.class.getName());

    // FUNGSI READ: Mengambil data untuk ditampilkan di Tabel
    public DefaultTableModel getDaftarMahasiswa() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIM");
        model.addColumn("Nama Mahasiswa");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            if (conn == null) return model;

            String sql = "SELECT nim, nama_mhs FROM mahasiswa ORDER BY nim ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nim"),
                    rs.getString("nama_mhs")
                });
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat memuat data mahasiswa", e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return model;
    }

    public boolean tambahMahasiswa(Mahasiswa_UKSW mhs) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal terhubung ke database.");

        PreparedStatement psLogin = null;
        PreparedStatement psMhs = null;

        try {
            conn.setAutoCommit(false); 

            String sqlLogin = "INSERT INTO pengguna_login (id_user, password, role) VALUES (?, ?, 'mhs')";
            psLogin = conn.prepareStatement(sqlLogin);
            psLogin.setString(1, mhs.getNIM());       
            psLogin.setString(2, "123456");          
            psLogin.executeUpdate();

            String sqlMhs = "INSERT INTO mahasiswa (nim, nama_mhs) VALUES (?, ?)";
            psMhs = conn.prepareStatement(sqlMhs);
            psMhs.setString(1, mhs.getNIM());       
            psMhs.setString(2, mhs.getNamaMahasiswa()); 
            psMhs.executeUpdate();

            conn.commit(); 
            return true;

        } catch (SQLException e) {
            if (conn != null) conn.rollback(); 
            throw e;
        } finally {
            if (psLogin != null) psLogin.close();
            if (psMhs != null) psMhs.close();
            closeResources(conn, null, null);
        }
    }

    public boolean hapusMahasiswa(String nim) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal terhubung ke database.");

        PreparedStatement psMhs = null;
        PreparedStatement psLogin = null;

        try {
            conn.setAutoCommit(false);
            String sqlDeleteMhs = "DELETE FROM mahasiswa WHERE nim = ?";
            psMhs = conn.prepareStatement(sqlDeleteMhs);
            psMhs.setString(1, nim);
            psMhs.executeUpdate();

            // 2. Hapus Akun Login
            String sqlDeleteLogin = "DELETE FROM pengguna_login WHERE id_user = ?";
            psLogin = conn.prepareStatement(sqlDeleteLogin);
            psLogin.setString(1, nim);
            psLogin.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (psMhs != null) psMhs.close();
            if (psLogin != null) psLogin.close();
            closeResources(conn, null, null);
        }
    }

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal menutup resource", e);
        }
    }
}