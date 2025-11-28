package Model;

public class MataKuliah_UKSW {
    private String kodeMk;
    private String namaMk;
    private String ruangan;
    private String waktu;
    private String sks;    // Diubah ke String sesuai database VARCHAR(75)
    private String hari;
    private String idDosen;

    // Constructor Lengkap
    public MataKuliah_UKSW(String kodeMk, String namaMk, String ruangan, String sks, String waktu, String hari, String idDosen) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.ruangan = ruangan;
        this.sks = sks;
        this.waktu = waktu;
        this.hari = hari;
        this.idDosen = idDosen;
    }

    // Getter dan Setter
    public String getKodeMk() { return kodeMk; }
    public void setKodeMk(String kodeMk) { this.kodeMk = kodeMk; }

    public String getNamaMk() { return namaMk; }
    public void setNamaMk(String namaMk) { this.namaMk = namaMk; }

    public String getRuangan() { return ruangan; }
    public void setRuangan(String ruangan) { this.ruangan = ruangan; }

    public String getSks() { return sks; } // Getter String
    public void setSks(String sks) { this.sks = sks; } // Setter String

    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }

    public String getHari() { return hari; }
    public void setHari(String hari) { this.hari = hari; }

    public String getIdDosen() { return idDosen; }
    public void setIdDosen(String idDosen) { this.idDosen = idDosen; }
}