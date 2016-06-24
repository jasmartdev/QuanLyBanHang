package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.NhomKH;

public class NhomKHController {
    private static NhomKHController instance;
    private List<NhomKH> nhomKHs;

    public static NhomKHController getInstance() {
        if (instance == null) {
            instance = new NhomKHController();
        }
        return instance;
    }

    public List<NhomKH> getNhomKHs() {
        return nhomKHs;
    }

    public void setNhomKHs(List<NhomKH> nhomKHs) {
        this.nhomKHs = nhomKHs;
    }
}
