package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.SanPham;

public class SanPhamController {
    private static SanPhamController instance;
    private List<SanPham> sanPhams;

    public static SanPhamController getInstance() {
        if (instance == null) {
            instance = new SanPhamController();
        }
        return instance;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
