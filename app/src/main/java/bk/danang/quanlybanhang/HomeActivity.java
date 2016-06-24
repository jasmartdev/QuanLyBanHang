package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.controller.PermissionController;

import java.util.List;

import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanVien;
import bk.danang.quanlybanhang.model.NhomKH;
import bk.danang.quanlybanhang.model.SanPham;
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

public class HomeActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_msg));
    }

    public void SelectFeature(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_ql_hoadon:
                intent = new Intent(this, QuanLyHoaDonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_sanpham:
                quanlySanPham();
                break;
            case R.id.btn_ql_loaisp:
                quanlyLoaiSP();
                break;
            case R.id.btn_ql_nhanhieu:
                quanlyNhanHieu();
                break;
            case R.id.btn_ql_khachang:
                quanlyKhachHang();
                break;
            case R.id.btn_ql_nhomkh:
                quanlyNhomKH();
                break;
            case R.id.btn_ql_doanhthu:
                intent = new Intent(this, QuanLyDoanhThuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ql_nhanvien:
                quanlyNhanVien();
                break;
        }
    }

    private void quanlyNhanVien(){
        progressDialog.show();
        NhanVienService nhanVienService = retrofit.create(NhanVienService.class);
        final Call<List<NhanVien>> call = nhanVienService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<NhanVien>>() {
            public void onResponse(Response<List<NhanVien>> response, Retrofit retrofit) {
                progressDialog.dismiss();
                NhanVienController.getInstance().setNhanViens(response.body());
                startActivity(new Intent(HomeActivity.this, QuanLyNhanVienActivity.class));
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quanlySanPham() {
        progressDialog.show();
        SanPhamService sanPhamService = retrofit.create(SanPhamService.class);

        final Call<List<SanPham>> call = sanPhamService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<SanPham>>() {
            public void onResponse(Response<List<SanPham>> response, Retrofit retrofit) {
                SanPhamController.getInstance().setSanPhams(response.body());
                LoaiSPService loaiSPService = retrofit.create(LoaiSPService.class);

                final Call<List<LoaiSP>> call = loaiSPService.getAll(PermissionController.getInstance().getAuthentication());
                call.enqueue(new Callback<List<LoaiSP>>() {
                    public void onResponse(Response<List<LoaiSP>> response, Retrofit retrofit) {
                        LoaiSPController.getInstance().setLoaiSPs(response.body());
                        NhanHieuService nhanHieuService = retrofit.create(NhanHieuService.class);

                        final Call<List<NhanHieu>> call = nhanHieuService.getAll(PermissionController.getInstance().getAuthentication());
                        call.enqueue(new Callback<List<NhanHieu>>() {
                            public void onResponse(Response<List<NhanHieu>> response, Retrofit retrofit) {
                                NhanHieuController.getInstance().setNhanHieus(response.body());
                                startActivity(new Intent(HomeActivity.this, QuanLySanPhamActivity.class));
                                progressDialog.dismiss();
                            }

                            public void onFailure(Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    public void onFailure(Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quanlyLoaiSP() {
        progressDialog.show();
        LoaiSPService loaiSPService = retrofit.create(LoaiSPService.class);

        final Call<List<LoaiSP>> call = loaiSPService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<LoaiSP>>() {
            public void onResponse(Response<List<LoaiSP>> response, Retrofit retrofit) {
                LoaiSPController.getInstance().setLoaiSPs(response.body());
                startActivity(new Intent(HomeActivity.this, QuanLyLoaiSPActivity.class));
                progressDialog.dismiss();
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quanlyNhanHieu() {
        progressDialog.show();
        NhanHieuService nhanHieuService = retrofit.create(NhanHieuService.class);

        final Call<List<NhanHieu>> call = nhanHieuService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<NhanHieu>>() {
            public void onResponse(Response<List<NhanHieu>> response, Retrofit retrofit) {
                NhanHieuController.getInstance().setNhanHieus(response.body());
                startActivity(new Intent(HomeActivity.this, QuanLyNhanHieuActivity.class));
                progressDialog.dismiss();
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quanlyKhachHang() {
        progressDialog.show();
        KhachHangService khachHangService = retrofit.create(KhachHangService.class);

        final Call<List<KhachHang>> call = khachHangService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<KhachHang>>() {
            public void onResponse(Response<List<KhachHang>> response, Retrofit retrofit) {
                KhachHangController.getInstance().setKhachHangs(response.body());
                NhomKHService nhanHieuService = retrofit.create(NhomKHService.class);

                final Call<List<NhomKH>> call = nhanHieuService.getAll(PermissionController.getInstance().getAuthentication());
                call.enqueue(new Callback<List<NhomKH>>() {
                    public void onResponse(Response<List<NhomKH>> response, Retrofit retrofit) {
                        NhomKHController.getInstance().setNhomKHs(response.body());
                        startActivity(new Intent(HomeActivity.this, QuanLyKhachHangActivity.class));
                        progressDialog.dismiss();
                    }

                    public void onFailure(Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quanlyNhomKH() {
        progressDialog.show();
        NhomKHService nhomKHService = retrofit.create(NhomKHService.class);

        final Call<List<NhomKH>> call = nhomKHService.getAll(PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<List<NhomKH>>() {
            public void onResponse(Response<List<NhomKH>> response, Retrofit retrofit) {
                NhomKHController.getInstance().setNhomKHs(response.body());
                startActivity(new Intent(HomeActivity.this, QuanLyNhomKHActivity.class));
                progressDialog.dismiss();
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
