package Model;

public class Mahasiswa_UKSW {
    private String nim;
    private String namaMhs;
    
    public Mahasiswa_UKSW(String nim, String namaMhs) {
        this.nim = nim;
        this.namaMhs = namaMhs;
    }

    public String getNIM() {
        return nim;
    }

    public void setNIM(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMhs;
    }

    public void setNamaMahasiswa(String namaMhs) {
        this.namaMhs = namaMhs;
    }
}