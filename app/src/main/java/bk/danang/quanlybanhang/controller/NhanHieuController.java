package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.NhanHieu;

public class NhanHieuController {
    private static NhanHieuController instance;
    private List<NhanHieu> nhanHieus;

    public static NhanHieuController getInstance() {
        if (instance == null) {
            instance = new NhanHieuController();
        }
        return instance;
    }

    public List<NhanHieu> getNhanHieus() {
        return nhanHieus;
    }

    public void setNhanHieus(List<NhanHieu> nhanHieus) {
        this.nhanHieus = nhanHieus;
    }
}
