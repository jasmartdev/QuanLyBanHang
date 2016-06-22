package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NhanHieuActivity extends AppCompatActivity {
    EditText ed_nhanhieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_hieu);
        ed_nhanhieu = (EditText) findViewById(R.id.ed_nhanhieu);
    }

    public void Save(View view) {

    }
}
