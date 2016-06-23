package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.KhachHang;

public class KhachHangController {
    private static KhachHangController instance;
    private KhachHang[] khachHangs;

    public static KhachHangController getInstance() {
        if (instance == null) {
            instance = new KhachHangController();
        }
        return instance;
    }

    public KhachHang[] getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(KhachHang[] khachHangs) {
        this.khachHangs = khachHangs;
    }
}
