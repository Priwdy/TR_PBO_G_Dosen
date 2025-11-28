package Controller;

import Model.Dosen_UKSW;
import Model.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ControllerTambahDosen {

    private static final Logger logger = Logger.getLogger(ControllerTambahDosen.class.getName());

    public DefaultTableModel getDaftarDosen() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NID");
        model.addColumn("Nama Dosen");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            if (conn == null) return model;

            String sql = "SELECT nid, nama_dosen FROM dosen ORDER BY nid ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nid"),
                    rs.getString("nama_dosen")
                });
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat memuat data dosen", e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return model;
    }

    
    public boolean tambahDosen(Dosen_UKSW dosen) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal terhubung ke database.");

        PreparedStatement psLogin = null;
        PreparedStatement psDosen = null;

        try {
            conn.setAutoCommit(false); 

            String sqlLogin = "INSERT INTO pengguna_login (id_user, password, role) VALUES (?, ?, 'dosen')";
            psLogin = conn.prepareStatement(sqlLogin);
            psLogin.setString(1, dosen.getNID());       
            psLogin.setString(2, "dosen_123");          
            psLogin.executeUpdate();

            String sqlDosen = "INSERT INTO dosen (nid, nama_dosen) VALUES (?, ?)";
            psDosen = conn.prepareStatement(sqlDosen);
            psDosen.setString(1, dosen.getNID());       
            psDosen.setString(2, dosen.getNamaDosen()); 
            psDosen.executeUpdate();

            conn.commit(); 
            return true;

        } catch (SQLException e) {
            if (conn != null) conn.rollback(); 
            throw e;
        } finally {
            if (psLogin != null) psLogin.close();
            if (psDosen != null) psDosen.close();
            closeResources(conn, null, null);
        }
    }

    public boolean hapusDosen(String nid) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal terhubung ke database.");

        PreparedStatement psDosen = null;
        PreparedStatement psLogin = null;

        try {
            conn.setAutoCommit(false);
            String sqlDeleteDosen = "DELETE FROM dosen WHERE nid = ?";
            psDosen = conn.prepareStatement(sqlDeleteDosen);
            psDosen.setString(1, nid);
            psDosen.executeUpdate();

            String sqlDeleteLogin = "DELETE FROM pengguna_login WHERE id_user = ?";
            psLogin = conn.prepareStatement(sqlDeleteLogin);
            psLogin.setString(1, nid);
            psLogin.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (psDosen != null) psDosen.close();
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