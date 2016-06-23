package bk.danang.quanlybanhang.controller;

import bk.danang.quanlybanhang.model.NhomKH;

public class NhomKHController {
    private static NhomKHController instance;
    private NhomKH[] nhomKHs;

    public static NhomKHController getInstance() {
        if (instance == null) {
            instance = new NhomKHController();
        }
        return instance;
    }

    public NhomKH[] getNhomKHs() {
        return nhomKHs;
    }

    public void setNhomKHs(NhomKH[] nhomKHs) {
        this.nhomKHs = nhomKHs;
    }
}
