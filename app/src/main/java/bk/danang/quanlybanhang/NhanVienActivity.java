package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class NhanVienActivity extends AppCompatActivity {
    EditText ed_nhomkh, ed_giam_gia, ed_ghi_chu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        ed_nhomkh = (EditText) findViewById(R.id.ed_nhomkh);
        ed_giam_gia = (EditText) findViewById(R.id.ed_giam_gia);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
    }

    public void Save(View view) {

    }
}
