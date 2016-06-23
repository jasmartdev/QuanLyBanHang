package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.model.KhachHang;

public class KhachHangActivity extends AppCompatActivity {
    EditText ed_khachhang, ed_dia_chi, ed_dien_thoai, ed_email, ed_nhomkh, ed_ghi_chu;
    RadioGroup rdg_gioi_tinh;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        ed_khachhang = (EditText) findViewById(R.id.ed_khachhang);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_dien_thoai = (EditText) findViewById(R.id.ed_dien_thoai);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_nhomkh = (EditText) findViewById(R.id.ed_nhomkh);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
        rdg_gioi_tinh = (RadioGroup) findViewById(R.id.rdg_gioi_tinh);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        if (id != -1) {
            KhachHang khachHang = KhachHangController.getInstance().getKhachHangs()[id];
            ed_khachhang.setText(khachHang.getName());
        }
    }

    public void Save(View view) {
        if (id != -1) {
            KhachHang khachHang = KhachHangController.getInstance().getKhachHangs()[id];
            khachHang.setName(ed_khachhang.getText().toString());
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
