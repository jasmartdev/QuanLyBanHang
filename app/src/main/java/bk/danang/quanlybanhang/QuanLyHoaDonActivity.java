package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.HoaDonAdapter;
import bk.danang.quanlybanhang.controller.HoaDonController;
import bk.danang.quanlybanhang.model.HoaDon;

public class QuanLyHoaDonActivity extends AppCompatActivity {
    private ListView lv_hoadon;
    private HoaDonAdapter hoaDonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hoa_don);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hoaDonAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_hoadon = (ListView) findViewById(R.id.lv_hoadon);
        HoaDon[] hoaDons = new HoaDon[8];
        HoaDonController.getInstance().setHoaDons(hoaDons);
        hoaDonAdapter = new HoaDonAdapter(this, hoaDons);
        lv_hoadon.setAdapter(hoaDonAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, HoaDonActivity.class);
        this.startActivity(intent);
    }
}
