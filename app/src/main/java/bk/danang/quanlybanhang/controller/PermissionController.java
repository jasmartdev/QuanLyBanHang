package bk.danang.quanlybanhang.controller;

public class PermissionController {
    private static PermissionController instance;
    private boolean isAdmin;
    private String authentication;

    public static PermissionController getInstance() {
        if (instance == null) {
            instance = new PermissionController();
        }
        return instance;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
