package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.LoaiSPAdapter;
import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.model.LoaiSP;

public class QuanLyLoaiSPActivity extends AppCompatActivity {
    private ListView lv_loaisp;
    private LoaiSPAdapter loaiSPAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_loaisp);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loaiSPAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_loaisp = (ListView) findViewById(R.id.lv_loaisp);
        LoaiSP[] loaiSPs = new LoaiSP[8];
        LoaiSPController.getInstance().setLoaiSPs(loaiSPs);
        loaiSPAdapter = new LoaiSPAdapter(this, loaiSPs);
        lv_loaisp.setAdapter(loaiSPAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, LoaiSPActivity.class);
        this.startActivity(intent);
    }
}
