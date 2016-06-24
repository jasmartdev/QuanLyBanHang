package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.LoaiSP;

public class LoaiSPActivity extends AppCompatActivity {
    private EditText ed_loaisp, ed_mo_ta;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaisp);
        ed_loaisp = (EditText) findViewById(R.id.ed_loaisp);
        ed_mo_ta = (EditText) findViewById(R.id.ed_mo_ta);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1 || !PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        if (id != -1) {
            LoaiSP loaiSP = LoaiSPController.getInstance().getLoaiSPs().get(id);
        }
    }

    public void Save(View view) {
        if (id != -1) {
            LoaiSP loaiSP = LoaiSPController.getInstance().getLoaiSPs().get(id);
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
