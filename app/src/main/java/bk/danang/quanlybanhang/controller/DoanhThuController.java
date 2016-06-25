package bk.danang.quanlybanhang.controller;

import java.util.ArrayList;
import java.util.List;

import bk.danang.quanlybanhang.model.DoanhThuResponse;
import bk.danang.quanlybanhang.model.HoaDon;

/**
 * Created by voqua on 6/25/2016.
 */
public class DoanhThuController {
    private static DoanhThuController instance;
    private DoanhThuResponse doanhThuResponse;

    public static DoanhThuController getInstance() {
        if (instance == null) {
            instance = new DoanhThuController();
        }
        return instance;
    }

    public DoanhThuResponse getDoanhThuResponse() {
        return doanhThuResponse;
    }

    public void setDoanhThuResponse(DoanhThuResponse doanhThuResponse) {
        this.doanhThuResponse = doanhThuResponse;
    }
}
