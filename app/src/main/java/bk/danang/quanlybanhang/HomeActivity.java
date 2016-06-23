package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                intent = new Intent(this, QuanLyLoaiSPActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_nhanhieu:
                intent = new Intent(this, QuanLyNhanHieuActivity.class);
                startActivity(intent);
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


}
