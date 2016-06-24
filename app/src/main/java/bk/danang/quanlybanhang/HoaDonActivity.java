package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import bk.danang.quanlybanhang.controller.HoaDonController;
import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.HoaDon;
import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.SanPham;

public class HoaDonActivity extends AppCompatActivity {
    Spinner spn_sanpham, spn_loaisp, ed_khachhang, spn_gia_ban, spn_nhomkh, spn_ten_nv;
    EditText ed_so_luong, ed_giam_gia, ed_dia_chi, ed_ghi_chu;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        spn_sanpham = (Spinner) findViewById(R.id.spn_sanpham);
        spn_loaisp = (Spinner) findViewById(R.id.spn_loaisp);
        spn_gia_ban = (Spinner) findViewById(R.id.spn_gia_ban);
        spn_nhomkh = (Spinner) findViewById(R.id.spn_nhomkh);
        spn_ten_nv = (Spinner) findViewById(R.id.spn_ten_nv);
        ed_so_luong = (EditText) findViewById(R.id.ed_so_luong);
        ed_giam_gia = (EditText) findViewById(R.id.ed_giam_gia);
        ed_khachhang = (Spinner) findViewById(R.id.ed_khachhang);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1 || !PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        spn_sanpham.setAdapter(createArrayAdapter(SanPhamController.getInstance().getSanPhams()));
        spn_loaisp.setAdapter(createArrayAdapter(LoaiSPController.getInstance().getLoaiSPs()));
        spn_gia_ban.setAdapter(createArrayAdapter(new String[]{"Gia goc", "Gia Vip"}));
        spn_nhomkh.setAdapter(createArrayAdapter(NhomKHController.getInstance().getNhomKHs()));
        spn_ten_nv.setAdapter(createArrayAdapter(NhanVienController.getInstance().getNhanViens()));
        ed_khachhang.setAdapter(createArrayAdapter(KhachHangController.getInstance().getKhachHangs()));
        
        if (id != -1) {
            HoaDon hoaDon = HoaDonController.getInstance().findById(id);

            ed_giam_gia.setText(hoaDon.getDiscountPercent()+"");
            ed_ghi_chu.setText(hoaDon.getDescription());




        }
    }

    private <T> ArrayAdapter<T> createArrayAdapter(List<T> data){
        return new ArrayAdapter<T>(this,android.R.layout.simple_spinner_dropdown_item, data);
    }

    private <T> ArrayAdapter<T> createArrayAdapter(T[] data){
        return new ArrayAdapter<T>(this,android.R.layout.simple_spinner_dropdown_item, data);
    }


    public void Save(View view) {
        if (id != -1) {
            //HoaDon hoaDon = HoaDonController.getInstance().getHoaDons()[id];
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
