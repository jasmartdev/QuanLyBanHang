package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.HoaDon;

public class HoaDonController {
    private static HoaDonController instance;
    private List<HoaDon> hoaDons;

    public static HoaDonController getInstance() {
        if (instance == null) {
            instance = new HoaDonController();
        }
        return instance;
    }

    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
