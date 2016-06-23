package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.NhanVien;

public class NhanVienController {
    private static NhanVienController instance;
    private NhanVien[] nhanViens;

    public static NhanVienController getInstance() {
        if (instance == null) {
            instance = new NhanVienController();
        }
        return instance;
    }

    public NhanVien[] getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(NhanVien[] nhanViens) {
        this.nhanViens = nhanViens;
    }
}
