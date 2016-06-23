package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.NhanHieuAdapter;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanHieuRequest;

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
        nhanHieuAdapter = new NhanHieuAdapter(this, NhanHieuController.getInstance().getNhanHieus());
        lv_nhanhieu.setAdapter(nhanHieuAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, NhanHieuActivity.class);
        this.startActivity(intent);
    }
}
