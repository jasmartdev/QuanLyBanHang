package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.NhanVienAdapter;
import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.model.NhanVien;

public class QuanLyNhanVienActivity extends AppCompatActivity {
    private ListView lv_nhanvien;
    private NhanVienAdapter nhanVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nhanVienAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_nhanvien = (ListView) findViewById(R.id.lv_nhanvien);
        nhanVienAdapter = new NhanVienAdapter(this, NhanVienController.getInstance().getNhanViens());
        lv_nhanvien.setAdapter(nhanVienAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, NhanVienActivity.class);
        this.startActivity(intent);
    }
}
