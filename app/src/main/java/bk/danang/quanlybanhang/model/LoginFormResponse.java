package bk.danang.quanlybanhang.model;

/**
 * Created by voqua on 6/24/2016.
 */
public class LoginFormResponse {
    private Role role;

    private String authentication;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
