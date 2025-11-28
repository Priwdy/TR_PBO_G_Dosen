package Model;

public class AkunPengguna {
    private String idUser;
    private String passwordLama;
    private String passwordBaru;

    public AkunPengguna(String idUser, String passwordLama, String passwordBaru) {
        this.idUser = idUser;
        this.passwordLama = passwordLama;
        this.passwordBaru = passwordBaru;
    }

    public String getIdUser() { 
        return idUser; 
    }
    
    public String getPasswordLama() { 
        return passwordLama; 
    }
    
    public String getPasswordBaru() { 
        return passwordBaru; 
    }
}