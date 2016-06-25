package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import bk.danang.quanlybanhang.controller.HoaDonController;
import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.HoaDon;
import bk.danang.quanlybanhang.model.HoaDonRequest;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoaiSPRequest;
import bk.danang.quanlybanhang.model.NhanVien;
import bk.danang.quanlybanhang.model.SanPham;
import bk.danang.quanlybanhang.util.Util;
import bk.danang.quanlybanhang.webinterface.HoaDonService;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HoaDonActivity extends AppCompatActivity {
    Spinner spn_sanpham, ed_khachhang, spn_gia_ban, spn_ten_nv;
    EditText ed_so_luong, ed_giam_gia, ed_ghi_chu;
    private int id;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        spn_sanpham = (Spinner) findViewById(R.id.spn_sanpham);
        spn_gia_ban = (Spinner) findViewById(R.id.spn_gia_ban);
        spn_ten_nv = (Spinner) findViewById(R.id.spn_ten_nv);
        ed_so_luong = (EditText) findViewById(R.id.ed_so_luong);
        ed_giam_gia = (EditText) findViewById(R.id.ed_giam_gia);
        ed_khachhang = (Spinner) findViewById(R.id.ed_khachhang);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1 || !PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_msg));
    }

    public void setObject() {
        spn_sanpham.setAdapter(createArrayAdapter(SanPhamController.getInstance().getSanPhams()));
        spn_gia_ban.setAdapter(createArrayAdapter(new String[]{"Giá gốc", "Giá bán lẻ", "Giá bán sỉ", "Giá vip"}));
        spn_ten_nv.setAdapter(createArrayAdapter(NhanVienController.getInstance().getNhanViens()));
        ed_khachhang.setAdapter(createArrayAdapter(KhachHangController.getInstance().getKhachHangs()));

        if (id != -1) {
            HoaDon hoaDon = HoaDonController.getInstance().findById(id);

            spn_sanpham.setSelection(SanPhamController.getInstance().getIndexOfId(hoaDon.getProductionId()));
            ed_khachhang.setSelection(KhachHangController.getInstance().getIndexOfId(hoaDon.getCustomerId()));
            spn_ten_nv.setSelection(NhanVienController.getInstance().getIndexOfId(hoaDon.getEmployeeId()));
            spn_gia_ban.setSelection(hoaDon.getRetailPrice());

            ed_giam_gia.setText(hoaDon.getDiscountPercent() + "");
            ed_so_luong.setText(hoaDon.getQuantity() + "");
            ed_ghi_chu.setText(hoaDon.getDescription());
        }
    }

    private <T> ArrayAdapter<T> createArrayAdapter(List<T> data) {
        return new ArrayAdapter<T>(this, android.R.layout.simple_spinner_dropdown_item, data);
    }

    private <T> ArrayAdapter<T> createArrayAdapter(T[] data) {
        return new ArrayAdapter<T>(this, android.R.layout.simple_spinner_dropdown_item, data);
    }


    public void Save(View view) {
        progressDialog.show();
        HoaDon hoaDon = new HoaDon();
        if (id != -1) {
            hoaDon = HoaDonController.getInstance().findById(id);
        }

        SanPham sanPham = ((SanPham) spn_sanpham.getSelectedItem());
        hoaDon.setProductionId(sanPham.getId());
        hoaDon.setEmployeeId(((NhanVien) spn_ten_nv.getSelectedItem()).getId());
        hoaDon.setCustomerId(((KhachHang) ed_khachhang.getSelectedItem()).getId());
        hoaDon.setDescription(ed_ghi_chu.getText().toString());

        hoaDon.setDiscountPercent(Util.GetNumber(ed_giam_gia));
        int[] giaban = new int[]{sanPham.getOriginPrice(), sanPham.getRetailPrice(), sanPham.getWholesalePrice(), sanPham.getVipPrice()};
        hoaDon.setPrice(giaban[spn_gia_ban.getSelectedItemPosition()]);
        hoaDon.setRetailPrice(spn_gia_ban.getSelectedItemPosition());
        hoaDon.setQuantity(Util.GetNumber(ed_so_luong));

        HoaDonService hoaDonService = retrofit.create(HoaDonService.class);
        HoaDonRequest hoaDonRequest = new HoaDonRequest();
        hoaDonRequest.setData(hoaDon);
        hoaDonRequest.setId(hoaDon.getId());
        hoaDonRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id == -1) {
            final Call<HoaDon> call = hoaDonService.them(hoaDonRequest);
            call.enqueue(new Callback<HoaDon>() {
                public void onResponse(Response<HoaDon> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    HoaDonController.getInstance().getHoaDons().add(response.body());
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(HoaDonActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<Object> call = hoaDonService.sua(hoaDonRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(HoaDonActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        HoaDonService hoaDonService = retrofit.create(HoaDonService.class);
        hoaDonService.xoa(id, PermissionController.getInstance().getAuthentication()).enqueue(new Callback<Object>() {
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                progressDialog.dismiss();
                HoaDon hoaDon = HoaDonController.getInstance().findById(id);
                HoaDonController.getInstance().getHoaDons().remove(hoaDon);
                finish();
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HoaDonActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
