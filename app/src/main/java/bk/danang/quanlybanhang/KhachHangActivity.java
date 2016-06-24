package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import bk.danang.quanlybanhang.adapter.SpinnerAdapter;
import bk.danang.quanlybanhang.controller.KhachHangController;
import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.KhachHangRequest;
import bk.danang.quanlybanhang.model.NhomKH;
import bk.danang.quanlybanhang.webinterface.KhachHangService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class KhachHangActivity extends AppCompatActivity {
    EditText ed_khachhang, ed_dia_chi, ed_dien_thoai, ed_email, ed_ghi_chu;
    RadioGroup rdg_gioi_tinh;
    RadioButton rdb_nam, rdb_nu;
    Spinner spn_nhomkh;
    private int id;
    KhachHang khachHang = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        ed_khachhang = (EditText) findViewById(R.id.ed_khachhang);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_dien_thoai = (EditText) findViewById(R.id.ed_dien_thoai);
        ed_email = (EditText) findViewById(R.id.ed_email);
        spn_nhomkh = (Spinner) findViewById(R.id.spn_nhomkh);
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_msg));
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
            System.out.println("Hoang gender" + " " + khachHang.getGender());
            if (khachHang.getGender() == 1) {
                rdb_nam.setChecked(true);
            } else {
                rdb_nu.setChecked(true);
            }
            ed_ghi_chu.setText(khachHang.getNote());
        }
        List<NhomKH> nhomKHList = NhomKHController.getInstance().getNhomKHs();
        spn_nhomkh.setAdapter(new SpinnerAdapter<NhomKH>(this, android.R.layout.simple_spinner_dropdown_item, nhomKHList));
        int nhomkhIndex = 0;
        if (id != -1) {
            for (int i = 1; i < nhomKHList.size(); i++) {
                if (nhomKHList.get(i).getId() == khachHang.getGroupId()) {
                    nhomkhIndex = i;
                    break;
                }
            }
        }
        spn_nhomkh.setSelection(nhomkhIndex);
    }

    public void Save(View view) {
        progressDialog.show();
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
                    progressDialog.dismiss();
                    KhachHangController.getInstance().getKhachHangs().add(khachHang);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(KhachHangActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        KhachHangService khachHangService = retrofit.create(KhachHangService.class);
        KhachHangRequest khachHangRequest = new KhachHangRequest();
        khachHangRequest.setData(khachHang);
        khachHangRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = khachHangService.xoa(khachHangRequest.getId(), PermissionController.getInstance().getAuthentication());
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(KhachHangActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addDataToObject() {
        khachHang.setName(ed_khachhang.getText().toString());
        khachHang.setAddress(ed_dia_chi.getText().toString());
        khachHang.setPhoneNumber(ed_dien_thoai.getText().toString());
        khachHang.setEmail(ed_email.getText().toString());
        khachHang.setGroupId(((NhomKH) spn_nhomkh.getSelectedItem()).getId());
        khachHang.setGender(rdb_nam.isChecked() ? 1 : 2);
        khachHang.setNote(ed_ghi_chu.getText().toString());
    }
}
