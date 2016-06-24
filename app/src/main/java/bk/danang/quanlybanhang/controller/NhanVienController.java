package bk.danang.quanlybanhang.controller;

import java.util.List;

import bk.danang.quanlybanhang.model.NhanVien;

public class NhanVienController {
    private static NhanVienController instance;
    private List<NhanVien> nhanViens;

    public static NhanVienController getInstance() {
        if (instance == null) {
            instance = new NhanVienController();
        }
        return instance;
    }

    public List<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public NhanVien findById(int id){
        for(NhanVien nhanvien: nhanViens){
            if(nhanvien.getId()==id){
                return nhanvien;
            }
        }
        return  null;
    }

    public void setNhanViens(List<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }
}
