package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class KhachHangActivity extends AppCompatActivity {
    EditText ed_khachhang, ed_dia_chi, ed_dien_thoai, ed_email, ed_nhomkh, ed_ghi_chu;
    RadioGroup rdg_gioi_tinh;

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
    }

    public void Save(View view) {

    }
}
