package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.SanPham;

public class NhanHieuActivity extends AppCompatActivity {
    private EditText ed_nhanhieu;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_hieu);
        ed_nhanhieu = (EditText) findViewById(R.id.ed_nhanhieu);
        if (!PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        if (id != -1) {
            SanPham NhanHieu = SanPhamController.getInstance().getSanPhams()[id];
            ed_nhanhieu.setText(NhanHieu.getName());
        }
    }

    public void Save(View view) {
        if (id != -1) {
            SanPham NhanHieu = SanPhamController.getInstance().getSanPhams()[id];
            NhanHieu.setName(ed_nhanhieu.getText().toString());
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
