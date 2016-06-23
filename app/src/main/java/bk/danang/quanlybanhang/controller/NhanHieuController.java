package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.NhanHieu;

public class NhanHieuController {
    private static NhanHieuController instance;
    private NhanHieu[] nhanHieus;

    public static NhanHieuController getInstance() {
        if (instance == null) {
            instance = new NhanHieuController();
        }
        return instance;
    }

    public NhanHieu[] getNhanHieus() {
        return nhanHieus;
    }

    public void setNhanHieus(NhanHieu[] nhanHieus) {
        this.nhanHieus = nhanHieus;
    }
}
