package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import bk.danang.quanlybanhang.adapter.NhomKHAdapter;
import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.model.NhomKH;

public class QuanLyNhomKHActivity extends AppCompatActivity {
    private ListView lv_nhomkh;
    private NhomKHAdapter nhomKHAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhomkh);
        setUpListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nhomKHAdapter.notifyDataSetChanged();
    }

    private void setUpListView() {
        lv_nhomkh = (ListView) findViewById(R.id.lv_nhomkh);
        nhomKHAdapter = new NhomKHAdapter(this, NhomKHController.getInstance().getNhomKHs());
        lv_nhomkh.setAdapter(nhomKHAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, NhomKHActivity.class);
        this.startActivity(intent);
    }
}