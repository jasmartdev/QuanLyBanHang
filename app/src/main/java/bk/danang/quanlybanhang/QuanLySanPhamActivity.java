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
        SanPham[] sanPhams = new SanPham[8];
        sanPhams[0] = new SanPham();
        sanPhams[0].setId(0);
        sanPhams[0].setName("San pham 1");
        sanPhams[0].setQuantity(2);
        sanPhams[1] = new SanPham();
        sanPhams[1].setId(1);
        sanPhams[1].setName("San pham 2");
        sanPhams[1].setQuantity(2);
        sanPhams[1].setOriginPrice(10000);
        sanPhams[2] = new SanPham();
        sanPhams[2].setId(2);
        sanPhams[2].setName("San pham 2");
        sanPhams[2].setQuantity(2);
        sanPhams[2].setOriginPrice(10000);
        sanPhams[2] = new SanPham();
        sanPhams[2].setId(2);
        sanPhams[2].setName("San pham 2");
        sanPhams[2].setQuantity(2);
        sanPhams[2].setOriginPrice(10000);
        sanPhams[3] = new SanPham();
        sanPhams[3].setId(3);
        sanPhams[3].setName("San pham 3");
        sanPhams[3].setQuantity(3);
        sanPhams[3].setOriginPrice(10000);
        sanPhams[4] = new SanPham();
        sanPhams[4].setId(4);
        sanPhams[4].setName("San pham 4");
        sanPhams[4].setQuantity(4);
        sanPhams[4].setOriginPrice(10000);
        sanPhams[5] = new SanPham();
        sanPhams[5].setId(5);
        sanPhams[5].setName("San pham 5");
        sanPhams[5].setQuantity(5);
        sanPhams[5].setOriginPrice(10000);
        sanPhams[6] = new SanPham();
        sanPhams[6].setId(6);
        sanPhams[6].setName("San pham 6");
        sanPhams[6].setQuantity(6);
        sanPhams[6].setOriginPrice(10000);
        sanPhams[7] = new SanPham();
        sanPhams[7].setId(7);
        sanPhams[7].setName("San pham 7");
        sanPhams[7].setQuantity(7);
        sanPhams[7].setOriginPrice(10000);
        SanPhamController.getInstance().setSanPhams(sanPhams);
        sanPhamAdapter = new SanPhamAdapter(this, sanPhams);
        lv_sanpham.setAdapter(sanPhamAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, SanPhamActivity.class);
        this.startActivity(intent);
    }
}
