package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import bk.danang.quanlybanhang.adapter.DoanhThuAdapter;
import bk.danang.quanlybanhang.controller.DoanhThuController;
import bk.danang.quanlybanhang.model.DoanhThuResponse;

public class QuanLyDoanhThuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_doanh_thu);
        DoanhThuResponse doanhThuResponse = DoanhThuController.getInstance().getDoanhThuResponse();
        ((TextView)findViewById(R.id.tong_doanh_thu)).setText(doanhThuResponse.getTotal()+ getString(R.string.chung_tien));
        ((ListView)findViewById(R.id.list_doanhthu)).setAdapter(new DoanhThuAdapter(this,
                DoanhThuController.getInstance().getDoanhThuResponse().getInvoices()));
    }
}
