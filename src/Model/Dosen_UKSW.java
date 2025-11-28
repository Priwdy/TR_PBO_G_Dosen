package Model;

public class Dosen_UKSW {
    private String nid;
    private String namaDsn;
    
    public Dosen_UKSW(String nid, String namaDsn) {
        this.nid = nid;
        this.namaDsn = namaDsn;
    }

    public String getNID() {
        return nid;
    }

    public void setNID(String nid) {
        this.nid = nid;
    }

    public String getNamaDosen() {
        return namaDsn;
    }

    public void setNamaDosen(String namaDsn) {
        this.namaDsn = namaDsn;
    }
}