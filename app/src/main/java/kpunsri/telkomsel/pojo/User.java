package kpunsri.telkomsel.pojo;

/**
 * Created by Gilbert on 2/6/2016.
 */
public class  User {

    public String name, email, password, unit;

    public User(String name, String email, String password, String unit){
        this.name = name;
        this.email= email;
        this.password = password;
        this.unit = unit;
    }

    public User(String email, String password){
        this.email=  email;
        this.password = password;
        this.name="";
        this.unit="";
    }
}
