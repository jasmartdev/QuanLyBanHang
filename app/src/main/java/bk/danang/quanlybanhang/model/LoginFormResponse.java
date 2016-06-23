package bk.danang.quanlybanhang.model;

/**
 * Created by voqua on 6/24/2016.
 */
public class LoginFormResponse {
    private int role;

    private String authentication;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
