package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.LoaiSP;

public class LoaiSPController {
    private static LoaiSPController instance;
    private LoaiSP[] loaiSPs;

    public static LoaiSPController getInstance() {
        if (instance == null) {
            instance = new LoaiSPController();
        }
        return instance;
    }

    public LoaiSP[] getLoaiSPs() {
        return loaiSPs;
    }

    public void setLoaiSPs(LoaiSP[] loaiSPs) {
        this.loaiSPs = loaiSPs;
    }
}
