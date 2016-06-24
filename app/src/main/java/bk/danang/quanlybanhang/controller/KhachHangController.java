package bk.danang.quanlybanhang.controller;

import java.util.ArrayList;
import java.util.List;

import bk.danang.quanlybanhang.model.KhachHang;

public class KhachHangController {
    private static KhachHangController instance;
    private List<KhachHang> khachHangs= new ArrayList<>();

    public static KhachHangController getInstance() {
        if (instance == null) {
            instance = new KhachHangController();
        }
        return instance;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public KhachHang findById(int id) {
        for (KhachHang khachhang : khachHangs) {
            if (khachhang.getId() == id) {
                return khachhang;
            }
        }
        return null;
    }

    public int getIndexOfId(int id){
        for(int i=0;i<khachHangs.size();i++){
            if(khachHangs.get(i).getId()==id){
                return i;
            }
        }
        return 0;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }
}
