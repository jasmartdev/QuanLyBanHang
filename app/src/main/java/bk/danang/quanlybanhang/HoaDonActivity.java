package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class HoaDonActivity extends AppCompatActivity {
    Spinner spn_sanpham, spn_loaisp, spn_gia_ban, spn_nhomkh, spn_ten_nv;
    EditText ed_so_luong, ed_giam_gia, ed_khachhang, ed_dia_chi, ed_ghi_chu;
    boolean isNew; //check if item is create new or update

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
        ed_khachhang = (EditText) findViewById(R.id.ed_khachhang);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
    }

    public void Save(View view) {

    }
}
