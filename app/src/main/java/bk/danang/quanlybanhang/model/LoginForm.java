package bk.danang.quanlybanhang.model;

import com.google.gson.annotations.SerializedName;

public class LoginForm {
    @SerializedName("user_name")
    private String userName;

    private String password;

    private String authentication;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
