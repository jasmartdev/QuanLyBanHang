package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.controller.PermissionController;

import java.io.IOException;
import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.webinterface.AuthenticationService;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import bk.danang.quanlybanhang.webinterface.LoaiSanPhamService;
import bk.danang.quanlybanhang.webinterface.NhanHieuService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HomeActivity extends AppCompatActivity  {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (!PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_ql_nhanvien)).setVisibility(View.INVISIBLE);
        }
    }

    public void SelectFeature(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_ql_hoadon:
                intent = new Intent(this, QuanLyHoaDonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_sanpham:
                intent = new Intent(this, QuanLySanPhamActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_loaisp:
                quanlyLoaiSP();
                break;
            case R.id.btn_ql_nhanhieu:
                quanlyNhanHieu();
                break;
            case R.id.btn_ql_khachang:
                intent = new Intent(this, QuanLyKhachHangActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_nhomkh:
                intent = new Intent(this, QuanLyNhomKHActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_doanhthu:
                intent = new Intent(this, QuanLyDoanhThuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_nhanvien:
                intent = new Intent(this, QuanLyNhanVienActivity.class);
                startActivity(intent);
                break;
        }
    }

    private  void quanlyLoaiSP(){
        LoaiSanPhamService nhanHieuService = retrofit.create(LoaiSanPhamService.class);

        final Call<List<LoaiSP>> call = nhanHieuService.getAll();
        call.enqueue(new Callback<List<LoaiSP>>() {
            public void onResponse(Response<List<LoaiSP>> response, Retrofit retrofit) {
                LoaiSPController.getInstance().setLoaiSPs(response.body());
                startActivity(new Intent(HomeActivity.this, QuanLyLoaiSPActivity.class));
            }

            public void onFailure(Throwable t) {

            }
        });
    }

    private  void quanlyNhanHieu(){

        NhanHieuService nhanHieuService = retrofit.create(NhanHieuService.class);

        final Call<List<NhanHieu>> call = nhanHieuService.getAll();
        call.enqueue(new Callback<List<NhanHieu>>() {
            public void onResponse(Response<List<NhanHieu>> response, Retrofit retrofit) {
                NhanHieuController.getInstance().setNhanHieus(response.body());
                startActivity(new Intent(HomeActivity.this, QuanLyNhanHieuActivity.class));
            }

            public void onFailure(Throwable t) {

            }
        });
    }
}
