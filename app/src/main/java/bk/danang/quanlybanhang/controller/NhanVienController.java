package bk.danang.quanlybanhang.controller;

import java.util.ArrayList;
import java.util.List;

import bk.danang.quanlybanhang.model.NhanVien;

public class NhanVienController {
    private static NhanVienController instance;
    private List<NhanVien> nhanViens= new ArrayList<>();

    public static NhanVienController getInstance() {
        if (instance == null) {
            instance = new NhanVienController();
        }
        return instance;
    }

    public List<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public int getIndexOfId(int id){
        for(int i=0;i<nhanViens.size();i++){
            if(nhanViens.get(i).getId()==id){
                return i;
            }
        }
        return 0;
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
