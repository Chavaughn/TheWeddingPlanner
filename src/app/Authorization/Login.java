package app.Authorization;

public class Login{
    private String iD;
    protected String password;


    public Login(String iD, String password){
        this.iD = iD;
    }

    public String getId(){
        return this.iD;
    }
}
