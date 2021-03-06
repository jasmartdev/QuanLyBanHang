package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanHieuRequest;
import bk.danang.quanlybanhang.model.SanPham;
import bk.danang.quanlybanhang.webinterface.NhanHieuService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class NhanHieuActivity extends AppCompatActivity {
    private EditText ed_nhanhieu;
    private int id;
    NhanHieu nhanHieu = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_hieu);
        ed_nhanhieu = (EditText) findViewById(R.id.ed_nhanhieu);
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
        if (nhanHieu == null) {
            nhanHieu = new NhanHieu();
        }
        if (id != -1) {
            for (NhanHieu nhanHieu1 : NhanHieuController.getInstance().getNhanHieus()) {
                if (nhanHieu1.getId() == id) {
                    nhanHieu = nhanHieu1;
                    break;
                }
            }
            ed_nhanhieu.setText(nhanHieu.getName());
        }
    }

    public void Save(View view) {
        progressDialog.show();
        addDataToObject();
        NhanHieuService nhanHieuService = retrofit.create(NhanHieuService.class);
        NhanHieuRequest nhanHieuRequest = new NhanHieuRequest();
        nhanHieuRequest.setData(nhanHieu);
        nhanHieuRequest.setId(nhanHieu.getId());
        nhanHieuRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = nhanHieuService.sua(nhanHieuRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(NhanHieuActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<NhanHieu> call = nhanHieuService.them(nhanHieuRequest);
            call.enqueue(new Callback<NhanHieu>() {
                public void onResponse(Response<NhanHieu> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    NhanHieuController.getInstance().getNhanHieus().add(nhanHieu);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(NhanHieuActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        NhanHieuService nhanHieuService = retrofit.create(NhanHieuService.class);
        if (id != -1) {
            final Call<Object> call = nhanHieuService.xoa(id, PermissionController.getInstance().getAuthentication());
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    NhanHieuController.getInstance().getNhanHieus().remove(nhanHieu);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(NhanHieuActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addDataToObject() {
        nhanHieu.setName(ed_nhanhieu.getText().toString());
    }
}
