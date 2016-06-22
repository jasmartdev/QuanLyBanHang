package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NhomKHActivity extends AppCompatActivity {
    EditText ed_nhanvien, ed_dia_chi, ed_dien_thoai, ed_email, ed_cong_viec, ed_ghi_chu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomkh);
        ed_nhanvien = (EditText) findViewById(R.id.ed_nhanvien);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_dien_thoai = (EditText) findViewById(R.id.ed_dien_thoai);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_cong_viec = (EditText) findViewById(R.id.ed_cong_viec);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
    }

    public void Save(View view) {

    }
}
