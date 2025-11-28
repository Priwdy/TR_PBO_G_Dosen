package Controller;

import Model.Koneksi;
import Model.MataKuliah_UKSW;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ControllerTambahMataKuliah {
    
    private static final Logger logger = Logger.getLogger(ControllerTambahMataKuliah.class.getName());

    public DefaultTableModel getDaftarMataKuliah() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode MK");
        model.addColumn("Nama Matakuliah");
        model.addColumn("Ruangan");
        model.addColumn("SKS");
        model.addColumn("Waktu");
        model.addColumn("Hari");
        model.addColumn("ID Dosen");

        try (Connection conn = Koneksi.getConnection()) {
            if (conn == null) return model;

            String sql = "SELECT kode_mk, nama_mk, ruangan, sks, waktu, hari, id_dosen FROM matakuliah ORDER BY kode_mk ASC";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("kode_mk"),
                        rs.getString("nama_mk"),
                        rs.getString("ruangan"),
                        rs.getString("sks"), // Ambil sebagai String
                        rs.getString("waktu"),
                        rs.getString("hari"),
                        rs.getString("id_dosen")
                    });
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error memuat data MK", e);
        }
        return model;
    }

    public boolean tambahMataKuliah(MataKuliah_UKSW mk) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal koneksi database.");

        String sql = "INSERT INTO matakuliah (kode_mk, nama_mk, ruangan, sks, waktu, hari, id_dosen) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mk.getKodeMk());
            ps.setString(2, mk.getNamaMk());
            ps.setString(3, mk.getRuangan());
            ps.setString(4, mk.getSks()); // Set sebagai String
            ps.setString(5, mk.getWaktu());
            ps.setString(6, mk.getHari());
            ps.setString(7, mk.getIdDosen());
            
            ps.executeUpdate();
            return true;
        } 
    }

    public boolean hapusMataKuliah(String kodeMk) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) throw new SQLException("Gagal koneksi database.");

        String sql = "DELETE FROM matakuliah WHERE kode_mk = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeMk);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }
    }
}