package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.NhanHieuAdapter;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.model.NhanHieu;

public class QuanLyNhanHieuActivity extends AppCompatActivity {
    private ListView lv_nhanhieu;
    private NhanHieuAdapter nhanHieuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_hieu);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nhanHieuAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_nhanhieu = (ListView) findViewById(R.id.lv_nhanhieu);
        NhanHieu[] nhanHieus = new NhanHieu[8];
        nhanHieus[0] = new NhanHieu();
        nhanHieus[0].setId(0);
        nhanHieus[0].setName("San pham 1");
        nhanHieus[1] = new NhanHieu();
        nhanHieus[1].setId(1);
        nhanHieus[1].setName("San pham 2");
        nhanHieus[2] = new NhanHieu();
        nhanHieus[2].setId(2);
        nhanHieus[2].setName("San pham 2");
        nhanHieus[2] = new NhanHieu();
        nhanHieus[2].setId(2);
        nhanHieus[2].setName("San pham 2");
        nhanHieus[3] = new NhanHieu();
        nhanHieus[3].setId(3);
        nhanHieus[3].setName("San pham 3");
        nhanHieus[4] = new NhanHieu();
        nhanHieus[4].setId(4);
        nhanHieus[4].setName("San pham 4");
        nhanHieus[5] = new NhanHieu();
        nhanHieus[5].setId(5);
        nhanHieus[5].setName("San pham 5");
        nhanHieus[6] = new NhanHieu();
        nhanHieus[6].setId(6);
        nhanHieus[6].setName("San pham 6");
        nhanHieus[7] = new NhanHieu();
        nhanHieus[7].setId(7);
        nhanHieus[7].setName("San pham 7");
        NhanHieuController.getInstance().setNhanHieus(nhanHieus);
        nhanHieuAdapter = new NhanHieuAdapter(this, nhanHieus);
        lv_nhanhieu.setAdapter(nhanHieuAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, NhanHieuActivity.class);
        this.startActivity(intent);
    }
}
