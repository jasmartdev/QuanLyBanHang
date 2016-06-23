package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;

public class LoaiSPController {
    private static LoaiSPController instance;
    private List<LoaiSP> loaiSPs;

    public static LoaiSPController getInstance() {
        if (instance == null) {
            instance = new LoaiSPController();
        }
        return instance;
    }

    public List<LoaiSP> getLoaiSPs() {
        return loaiSPs;
    }

    public void setLoaiSPs(List<LoaiSP> loaiSPs) {
        this.loaiSPs = loaiSPs;
    }
}
