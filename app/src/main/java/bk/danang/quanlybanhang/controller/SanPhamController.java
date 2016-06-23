package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.SanPham;

public class SanPhamController {
    private static SanPhamController instance;
    private SanPham[] sanPhams;

    public static SanPhamController getInstance() {
        if (instance == null) {
            instance = new SanPhamController();
        }
        return instance;
    }

    public SanPham[] getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(SanPham[] sanPhams) {
        this.sanPhams = sanPhams;
    }
}
