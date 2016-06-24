package bk.danang.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.SanPhamAdapter;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.SanPham;

public class QuanLySanPhamActivity extends AppCompatActivity {
    private ListView lv_sanpham;
    private SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sanPhamAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_sanpham = (ListView) findViewById(R.id.lv_sanpham);
        sanPhamAdapter = new SanPhamAdapter(this, SanPhamController.getInstance().getSanPhams());
        lv_sanpham.setAdapter(sanPhamAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, SanPhamActivity.class);
        this.startActivity(intent);
    }
}
