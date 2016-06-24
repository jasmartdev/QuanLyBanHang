package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import bk.danang.quanlybanhang.adapter.NhanHieuAdapter;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanHieuRequest;
import bk.danang.quanlybanhang.webinterface.AuthenticationService;
import bk.danang.quanlybanhang.webinterface.NhanHieuService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class QuanLyNhanHieuActivity extends AppCompatActivity implements Callback<NhanHieu> {
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.WEB_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NhanHieuService stackOverflowAPI = retrofit.create(NhanHieuService.class);

        final Call<List<NhanHieu>> call = stackOverflowAPI.getAll();
        lv_nhanhieu = (ListView) findViewById(R.id.lv_nhanhieu);
        nhanHieuAdapter = new NhanHieuAdapter(this, NhanHieuController.getInstance().getNhanHieus());
        lv_nhanhieu.setAdapter(nhanHieuAdapter);
    }

    public void Add(View view) {
        Intent intent = new Intent(this, NhanHieuActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onResponse(Response<NhanHieu> response, Retrofit retrofit) {
        if (response == null || response.body() == null) {
            Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
    }
}
