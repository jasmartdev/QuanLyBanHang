package bk.danang.quanlybanhang.controller;

import java.util.ArrayList;
import java.util.List;

import bk.danang.quanlybanhang.model.SanPham;

public class SanPhamController {
    private static SanPhamController instance;
    private List<SanPham> sanPhams = new ArrayList<>();

    public static SanPhamController getInstance() {
        if (instance == null) {
            instance = new SanPhamController();
        }
        return instance;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public SanPham findById(int id) {
        for (SanPham sanpham : sanPhams) {
            if (sanpham.getId() == id) {
                return sanpham;
            }
        }
        return null;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
