package entity;

import java.sql.Timestamp;
import java.util.Dictionary;
import java.util.Hashtable;

public class Users {
    private int uid;
    private String userName;
    private String password;
    private String email;
    private Timestamp regTime;
    private boolean isAdmin;

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public Dictionary toDictionary(){
        Dictionary tmp = new Hashtable();
        tmp.put("uid", getUid());
        tmp.put("nameName", getUserName());
        tmp.put("email",getEmail());
        tmp.put("regTime",getRegTime());
        tmp.put("isAdmin",isAdmin());

        return tmp;
    }
}
