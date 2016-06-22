package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoaiSPActivity extends AppCompatActivity {
    EditText ed_loaisp, ed_mo_ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaisp);
        ed_loaisp = (EditText) findViewById(R.id.ed_loaisp);
        ed_mo_ta = (EditText) findViewById(R.id.ed_mo_ta);
    }

    public void Save(View view) {

    }
}
