package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class SanPhamActivity extends AppCompatActivity {
    Spinner spn_loaisp, spn_hieu;
    EditText ed_sanpham, ed_so_luong, ed_gia_goc, ed_gia_vip, ed_gia_si, ed_gia_le;
    CheckBox cb_con_hang;

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
    }

    public void Save(View view) {

    }
}
