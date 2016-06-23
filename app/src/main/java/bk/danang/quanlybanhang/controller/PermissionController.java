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

    public boolean getSanPhams() {
        return isAdmin;
    }

    public void setSanPhams(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
