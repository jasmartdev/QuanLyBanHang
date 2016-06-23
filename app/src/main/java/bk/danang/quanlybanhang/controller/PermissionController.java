package bk.danang.quanlybanhang.controller;

public class PermissionController {
    private static PermissionController instance;
    private boolean isAdmin;

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
}
