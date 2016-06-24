package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.KhachHang;

public class NhomKHActivity extends AppCompatActivity {
    private EditText ed_nhomkh, ed_giam_gia, ed_ghi_chu;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomkh);
        ed_nhomkh = (EditText) findViewById(R.id.ed_nhomkh);
        ed_giam_gia = (EditText) findViewById(R.id.ed_giam_gia);
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
            KhachHang khachHang = KhachHangController.getInstance().getKhachHangs().get(id);
        }
    }

    public void Save(View view) {
        if (id != -1) {
            KhachHang khachHang = KhachHangController.getInstance().getKhachHangs().get(id);
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
