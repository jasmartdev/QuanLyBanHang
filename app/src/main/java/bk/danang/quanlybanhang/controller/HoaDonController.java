package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.HoaDon;

public class HoaDonController {
    private static HoaDonController instance;
    private HoaDon[] hoaDons;

    public static HoaDonController getInstance() {
        if (instance == null) {
            instance = new HoaDonController();
        }
        return instance;
    }

    public HoaDon[] getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(HoaDon[] hoaDons) {
        this.hoaDons = hoaDons;
    }
}
