package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.NhanVien;

public class NhanVienActivity extends AppCompatActivity {
    private EditText ed_nhanvien, ed_dia_chi, ed_dien_thoai, ed_email, ed_cong_viec, ed_ghi_chu;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        ed_nhanvien = (EditText) findViewById(R.id.ed_nhanvien);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_dien_thoai = (EditText) findViewById(R.id.ed_dien_thoai);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_cong_viec = (EditText) findViewById(R.id.ed_cong_viec);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1 || !PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        if (id != -1) {
            NhanVien nhanVien = NhanVienController.getInstance().getNhanViens()[id];
        }
    }

    public void Save(View view) {
        if (id != -1) {
            NhanVien nhanVien = NhanVienController.getInstance().getNhanViens()[id];
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
