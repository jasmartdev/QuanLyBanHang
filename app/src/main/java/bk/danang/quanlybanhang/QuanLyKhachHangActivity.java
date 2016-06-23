package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.KhachHangAdapter;
import bk.danang.quanlybanhang.adapter.SanPhamAdapter;
import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.SanPham;

public class QuanLyKhachHangActivity extends AppCompatActivity {
    private ListView lv_khachhang;
    private KhachHangAdapter khachHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_khach_hang);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        khachHangAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_khachhang = (ListView) findViewById(R.id.lv_khachhang);
        KhachHang[] khachHangs = new KhachHang[2];
        KhachHangController.getInstance().setKhachHangs(khachHangs);
        khachHangAdapter = new KhachHangAdapter(this, khachHangs);
        lv_khachhang.setAdapter(khachHangAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, KhachHangActivity.class);
        this.startActivity(intent);
    }
}
