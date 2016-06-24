package bk.danang.quanlybanhang.util;

import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import bk.danang.quanlybanhang.AppConstant;
import bk.danang.quanlybanhang.QuanLyHoaDonActivity;
import bk.danang.quanlybanhang.QuanLyKhachHangActivity;
import bk.danang.quanlybanhang.QuanLyLoaiSPActivity;
import bk.danang.quanlybanhang.QuanLyNhanVienActivity;
import bk.danang.quanlybanhang.QuanLyNhomKHActivity;
import bk.danang.quanlybanhang.QuanLySanPhamActivity;
import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.controller.HoaDonController;
import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.HoaDon;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanVien;
import bk.danang.quanlybanhang.model.NhomKH;
import bk.danang.quanlybanhang.model.SanPham;
import bk.danang.quanlybanhang.webinterface.HoaDonService;
import bk.danang.quanlybanhang.webinterface.KhachHangService;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import bk.danang.quanlybanhang.webinterface.NhanHieuService;
import bk.danang.quanlybanhang.webinterface.NhanVienService;
import bk.danang.quanlybanhang.webinterface.NhomKHService;
import bk.danang.quanlybanhang.webinterface.SanPhamService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by voqua on 6/24/2016.
 */
public class LoadAllData {
    private Runnable onDone;
    private Runnable onFail;
    private boolean isFail = false;
    private int finish = 0;
    private int counter = 0;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public LoadAllData(Runnable onDone, Runnable onFail){
        this.onDone = onDone;
        this.onFail = onFail;
    }

    public void start(){
        counter=0;
        loadLoaiSP();
        loadKhachHang();
        loadNhomKH();
        loadSanPham();
        loadNhanHieu();
        loadHoaDon();
        loadNhanVien();
    }

    private void loadLoaiSP(){
        LoaiSPService loaiSPService = retrofit.create(LoaiSPService.class);

        final Call<List<LoaiSP>> call = loaiSPService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<LoaiSP>>() {
            public void onResponse(Response<List<LoaiSP>> response, Retrofit retrofit) {
                LoaiSPController.getInstance().setLoaiSPs(response.body());
                checkDone(true);
            }

            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void loadKhachHang(){
        KhachHangService khachHangService = retrofit.create(KhachHangService.class);

        final Call<List<KhachHang>> call = khachHangService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<KhachHang>>() {
            public void onResponse(Response<List<KhachHang>> response, Retrofit retrofit) {
                KhachHangController.getInstance().setKhachHangs(response.body());
                checkDone(true);
            }

            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void loadNhomKH(){
        NhomKHService nhomKHService = retrofit.create(NhomKHService.class);

        final Call<List<NhomKH>> call = nhomKHService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<NhomKH>>() {
            public void onResponse(Response<List<NhomKH>> response, Retrofit retrofit) {
                NhomKHController.getInstance().setNhomKHs(response.body());
                checkDone(true);
            }

            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void loadSanPham(){
        SanPhamService sanPhamService = retrofit.create(SanPhamService.class);

        final Call<List<SanPham>> call = sanPhamService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<SanPham>>() {
            public void onResponse(Response<List<SanPham>> response, Retrofit retrofit) {
                SanPhamController.getInstance().setSanPhams(response.body());
                checkDone(true);
            }

            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void loadNhanHieu(){
        NhanHieuService nhanHieuService = retrofit.create(NhanHieuService.class);

        final Call<List<NhanHieu>> call = nhanHieuService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<NhanHieu>>() {
            public void onResponse(Response<List<NhanHieu>> response, Retrofit retrofit) {
                checkDone(true);
            }

            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void loadHoaDon(){
        HoaDonService hoaDonService = retrofit.create(HoaDonService.class);
        final Call<List<HoaDon>> call = hoaDonService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<HoaDon>>() {
            public void onResponse(Response<List<HoaDon>> response, Retrofit retrofit) {
                checkDone(true);
            }

            @Override
            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void loadNhanVien(){
        NhanVienService nhanVienService = retrofit.create(NhanVienService.class);
        final Call<List<NhanVien>> call = nhanVienService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<NhanVien>>() {
            public void onResponse(Response<List<NhanVien>> response, Retrofit retrofit) {
                NhanVienController.getInstance().setNhanViens(response.body());
                checkDone(true);
            }

            public void onFailure(Throwable t) {
                checkDone(false);
            }
        });
    }

    private void checkDone(boolean isSucccess){
        if(!isSucccess){
            isFail = true;
        }
        counter ++;
        if(counter>=7){
            if(isFail){
                onFail.run();
            }else{
                onDone.run();
            }
        }
    }
}
