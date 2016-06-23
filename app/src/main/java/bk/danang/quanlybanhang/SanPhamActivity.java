package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.SanPham;

public class SanPhamActivity extends AppCompatActivity {
    private Spinner spn_loaisp, spn_hieu;
    private EditText ed_sanpham, ed_so_luong, ed_gia_goc, ed_gia_vip, ed_gia_si, ed_gia_le;
    private CheckBox cb_con_hang;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        spn_loaisp = (Spinner) findViewById(R.id.spn_loaisp);
        spn_hieu = (Spinner) findViewById(R.id.spn_hieu);
        ed_sanpham = (EditText) findViewById(R.id.ed_sanpham);
        ed_so_luong = (EditText) findViewById(R.id.ed_so_luong);
        ed_gia_goc = (EditText) findViewById(R.id.ed_gia_goc);
        ed_gia_vip = (EditText) findViewById(R.id.ed_gia_vip);
        ed_gia_si = (EditText) findViewById(R.id.ed_gia_si);
        ed_gia_le = (EditText) findViewById(R.id.ed_gia_le);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        if (id != -1) {
            SanPham sanPham = SanPhamController.getInstance().getSanPhams()[id];
            ed_sanpham.setText(sanPham.getName());
            ed_so_luong.setText(Integer.toString(sanPham.getNumber()));
        }
    }

    public void Save(View view) {
        if (id != -1) {
            SanPham sanPham = SanPhamController.getInstance().getSanPhams()[id];
            sanPham.setName(ed_sanpham.getText().toString());
        }
        this.finish();
    }

    public void Delete(View view) {
    }
}
