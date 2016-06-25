package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.KhachHangRequest;
import bk.danang.quanlybanhang.model.NhanVien;
import bk.danang.quanlybanhang.model.NhanVienRequest;
import bk.danang.quanlybanhang.webinterface.KhachHangService;
import bk.danang.quanlybanhang.webinterface.NhanVienService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class NhanVienActivity extends AppCompatActivity {
    private EditText ed_nhanvien, ed_dia_chi, ed_dien_thoai, ed_email,
            ed_cong_viec, ed_ghi_chu, ed_ten_dang_nhap, ed_mat_khau;
    private RadioButton rbGenderMale, rbGenderFemale;
    private int id;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        ed_nhanvien = (EditText) findViewById(R.id.ed_nhanvien);
        ed_dia_chi = (EditText) findViewById(R.id.ed_dia_chi);
        ed_dien_thoai = (EditText) findViewById(R.id.ed_dien_thoai);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_cong_viec = (EditText) findViewById(R.id.ed_cong_viec);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
        ed_ten_dang_nhap = (EditText) findViewById(R.id.ed_dangnhap);
        ed_mat_khau = (EditText) findViewById(R.id.ed_password);
        rbGenderMale = (RadioButton) findViewById(R.id.rdb_nam);
        rbGenderFemale = (RadioButton) findViewById(R.id.rdb_nu);

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
        if (id != -1) {
            NhanVien nhanVien = NhanVienController.getInstance().findById(id);
            ed_mat_khau.setText("");
            ed_nhanvien.setText(nhanVien.getName());
            ed_dia_chi.setText(nhanVien.getAddress());
            ed_dien_thoai.setText(nhanVien.getPhoneNumber());
            ed_email.setText(nhanVien.getEmail());
            ed_cong_viec.setText(nhanVien.getJobTitle());
            ed_ghi_chu.setText(nhanVien.getNote());
            ed_ten_dang_nhap.setText(nhanVien.getLoginName());
            if (nhanVien.getGender() == 1) {
                rbGenderMale.setChecked(true);
            } else {
                rbGenderFemale.setChecked(true);
            }
        }
    }

    public void Save(View view) {
        progressDialog.show();
        NhanVien nhanVien = (id == -1) ? new NhanVien() : NhanVienController.getInstance().findById(id);

        nhanVien.setPassword(ed_mat_khau.getText().toString());
        nhanVien.setAddress(ed_dia_chi.getText().toString());
        nhanVien.setEmail(ed_email.getText().toString());
        if (rbGenderMale.isChecked()) {
            nhanVien.setGender(1);
        } else {
            nhanVien.setGender(2);
        }
        nhanVien.setName(ed_nhanvien.getText().toString());
        nhanVien.setPhoneNumber(ed_dien_thoai.getText().toString());
        nhanVien.setNote(ed_ghi_chu.getText().toString());
        nhanVien.setJobTitle(ed_cong_viec.getText().toString());
        nhanVien.setLoginName(ed_ten_dang_nhap.getText().toString());

        NhanVienService nhanVienService = retrofit.create(NhanVienService.class);
        NhanVienRequest nhanVienRequest = new NhanVienRequest();
        nhanVienRequest.setData(nhanVien);
        nhanVienRequest.setId(nhanVien.getId());
        nhanVienRequest.setAuthentication(PermissionController.getInstance().getAuthentication());

        if (id == -1) {
            final Call<NhanVien> call = nhanVienService.them(nhanVienRequest);
            call.enqueue(new Callback<NhanVien>() {
                public void onResponse(Response<NhanVien> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    NhanVienController.getInstance().getNhanViens().add(response.body());
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(NhanVienActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<Object> call = nhanVienService.sua(nhanVienRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(NhanVienActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        NhanVienService nhanVienService = retrofit.create(NhanVienService.class);
        final Call<Object> call = nhanVienService.xoa(id, PermissionController.getInstance().getAuthentication());
        call.enqueue(new Callback<Object>() {
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                progressDialog.dismiss();
                finish();
            }

            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NhanVienActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
