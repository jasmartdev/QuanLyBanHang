package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.KhachHangRequest;
import bk.danang.quanlybanhang.webinterface.KhachHangService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class KhachHangActivity extends AppCompatActivity {
    EditText ed_khachhang, ed_dia_chi, ed_dien_thoai, ed_email, ed_nhomkh, ed_ghi_chu;
    RadioGroup rdg_gioi_tinh;
    RadioButton rdb_nam, rdb_nu;
    private int id;
    KhachHang khachHang = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        ed_khachhang = (EditText) findViewById(R.id.ed_khachhang);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_dien_thoai = (EditText) findViewById(R.id.ed_dien_thoai);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_nhomkh = (EditText) findViewById(R.id.ed_nhomkh);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
        rdg_gioi_tinh = (RadioGroup) findViewById(R.id.rdg_gioi_tinh);
        rdb_nam = (RadioButton) findViewById(R.id.rdb_nam);
        rdb_nu = (RadioButton) findViewById(R.id.rdb_nu);
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1 || !PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
    }

    public void setObject() {
        if (khachHang == null) {
            khachHang = new KhachHang();
        }
        if (id != -1) {
            for (KhachHang khachHang1 : KhachHangController.getInstance().getKhachHangs()) {
                if (khachHang1.getId() == id) {
                    khachHang = khachHang1;
                    break;
                }
            }
            ed_khachhang.setText(khachHang.getName());
            ed_dia_chi.setText(khachHang.getAddress());
            ed_dien_thoai.setText(khachHang.getPhoneNumber());
            ed_email.setText(khachHang.getEmail());
            ed_nhomkh.setText(khachHang.getGroupId());
            rdb_nam.setSelected(khachHang.getGender() == 1);
            rdb_nu.setSelected(khachHang.getGender() == 2);
            ed_ghi_chu.setText(khachHang.getNote());
        }
    }

    public void Save(View view) {
        addDataToObject();
        KhachHangService khachHangService = retrofit.create(KhachHangService.class);
        KhachHangRequest khachHangRequest = new KhachHangRequest();
        khachHangRequest.setData(khachHang);
        khachHangRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = khachHangService.sua(khachHangRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(KhachHangActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<KhachHang> call = khachHangService.them(khachHangRequest);
            call.enqueue(new Callback<KhachHang>() {
                public void onResponse(Response<KhachHang> response, Retrofit retrofit) {
                    KhachHangController.getInstance().getKhachHangs().add(khachHang);
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(KhachHangActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
        this.finish();
    }

    public void Delete(View view) {
        KhachHangService khachHangService = retrofit.create(KhachHangService.class);
        KhachHangRequest khachHangRequest = new KhachHangRequest();
        khachHangRequest.setData(khachHang);
        khachHangRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = khachHangService.xoa(khachHangRequest.getId(), PermissionController.getInstance().getAuthentication());
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(KhachHangActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
        this.finish();
    }

    public void addDataToObject() {
        khachHang.setName(ed_khachhang.getText().toString());
//        khachHang.setPhoneNumber();
//
//        khachHang.setGroupId(ed_nhomkh.getText().toString());
//        khachHang.setNote(ed_ghi_chu.getText().toString());
//
//        ed_dia_chi.setText(khachHang.getAddress());
//        ed_dien_thoai.setText(khachHang.getAddress());
//        ed_email.setText(khachHang.getAddress());
//        ed_nhomkh.setText(khachHang.getAddress());
//        ed_ghi_chu.setText(khachHang.getAddress());
//        ed_dien_thoai.setText(khachHang.getAddress());
//        rdb_nam.setSelected(khachHang.getGender() == 1);
//        rdb_nu.setSelected(khachHang.getGender() == 2);
//        ed_ghi_chu.setText(khachHang.getNote());
    }
}
