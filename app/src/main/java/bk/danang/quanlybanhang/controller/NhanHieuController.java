package bk.danang.quanlybanhang.controller;

import java.util.ArrayList;
import java.util.List;

import bk.danang.quanlybanhang.model.NhanHieu;

public class NhanHieuController {
    private static NhanHieuController instance;
    private List<NhanHieu> nhanHieus= new ArrayList<>();

    public static NhanHieuController getInstance() {
        if (instance == null) {
            instance = new NhanHieuController();
        }
        return instance;
    }

    public List<NhanHieu> getNhanHieus() {
        return nhanHieus;
    }

    public int getIndexOfId(int id){
        for(int i=0;i<nhanHieus.size();i++){
            if(nhanHieus.get(i).getId()==id){
                return i;
            }
        }
        return 0;
    }

    public NhanHieu findById(int id){
        for(NhanHieu nhanhieu: nhanHieus){
            if(nhanhieu.getId()==id){
                return nhanhieu;
            }
        }
        return  null;
    }
    public void setNhanHieus(List<NhanHieu> nhanHieus) {
        this.nhanHieus = nhanHieus;
    }
}
