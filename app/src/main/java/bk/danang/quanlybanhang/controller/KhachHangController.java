package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.KhachHang;

public class KhachHangController {
    private static KhachHangController instance;
    private List<KhachHang> khachHangs;

    public static KhachHangController getInstance() {
        if (instance == null) {
            instance = new KhachHangController();
        }
        return instance;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
        System.out.println("Hoang "+" "+khachHangs.size());
    }
}
