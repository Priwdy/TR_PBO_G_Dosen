package Model;

public class Nilai_UKSW {
    private String kodeMk;
    private String nilai;
    private float ak;

    public Nilai_UKSW(String kodeMk, String nilai, float ak) {
        this.kodeMk = kodeMk;
        this.nilai = nilai;
        this.ak = ak;
    }

    public String getKodeMk() { 
        return kodeMk; 
    }
    
    public void setKodeMk(String kodeMk) { 
        this.kodeMk = kodeMk; 
    }

    public String getNilai() { 
        return nilai; 
    }
    public void setNilai(String nilai) { 
        this.nilai = nilai; 
    }

    public float getAk() { 
        return ak; 
    }
    public void setAk(float ak) { 
        this.ak = ak; 
    }
}
