package Controller;

import Model.Koneksi;
import Model.Nilai_UKSW;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ControllerInputNilai {
    
    private static final Logger logger = Logger.getLogger(ControllerInputNilai.class.getName());

    // 1. READ: Ambil semua data nilai
    public DefaultTableModel getAllNilai() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Mata Kuliah");
        model.addColumn("Nilai");
        model.addColumn("AK");

        try (Connection conn = Koneksi.getConnection()) {
            if (conn == null) return model;

            String sql = "SELECT kode_mk, nilai, ak FROM input_nilai ORDER BY kode_mk ASC";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("kode_mk"),
                        rs.getString("nilai"),
                        rs.getFloat("ak")
                    });
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error memuat data nilai", e);
        }
        return model;
    }

    // 2. CREATE: Tambah Nilai (Cek Foreign Key)
    public boolean insertNilai(Nilai_UKSW data) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal koneksi database.");

        String sql = "INSERT INTO input_nilai (kode_mk, nilai, ak) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, data.getKodeMk());
            ps.setString(2, data.getNilai());
            ps.setFloat(3, data.getAk());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Error 1452 = Cannot add or update a child row: a foreign key constraint fails
            if (e.getErrorCode() == 1452) {
                throw new SQLException("Kode MK '" + data.getKodeMk() + "' tidak ditemukan di Data Matakuliah (Foreign Key Error).");
            } else {
                throw e; // Lempar error lain ke View
            }
        }
    }

    // 3. UPDATE: Ubah Nilai
    public boolean updateNilai(Nilai_UKSW data) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal koneksi database.");

        String sql = "UPDATE input_nilai SET nilai = ?, ak = ? WHERE kode_mk = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, data.getNilai());
            ps.setFloat(2, data.getAk());
            ps.setString(3, data.getKodeMk());
            
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }

    // 4. DELETE: Hapus Nilai
    public boolean deleteNilai(String kodeMk) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal koneksi database.");

        String sql = "DELETE FROM input_nilai WHERE kode_mk = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeMk);
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
}