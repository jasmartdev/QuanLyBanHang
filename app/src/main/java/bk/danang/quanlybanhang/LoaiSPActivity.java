package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoaiSPRequest;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoaiSPActivity extends AppCompatActivity {
    private EditText ed_loaisp;
    private int id;
    LoaiSP loaiSP = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaisp);
        ed_loaisp = (EditText) findViewById(R.id.ed_loaisp);
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
        if (loaiSP == null) {
            loaiSP = new LoaiSP();
        }
        if (id != -1) {
            for (LoaiSP loaiSP1 : LoaiSPController.getInstance().getLoaiSPs()) {
                if (loaiSP1.getId() == id) {
                    loaiSP = loaiSP1;
                    break;
                }
            }
            ed_loaisp.setText(loaiSP.getName());
        }
    }

    public void Save(View view) {
        progressDialog.show();
        addDataToObject();
        LoaiSPService loaiSPService = retrofit.create(LoaiSPService.class);
        LoaiSPRequest loaiSPRequest = new LoaiSPRequest();
        loaiSPRequest.setData(loaiSP);
        loaiSPRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = loaiSPService.sua(loaiSPRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(LoaiSPActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<LoaiSP> call = loaiSPService.them(loaiSPRequest);
            call.enqueue(new Callback<LoaiSP>() {
                public void onResponse(Response<LoaiSP> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    LoaiSPController.getInstance().getLoaiSPs().add(loaiSP);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(LoaiSPActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        LoaiSPService loaiSPService = retrofit.create(LoaiSPService.class);
        LoaiSPRequest loaiSPRequest = new LoaiSPRequest();
        loaiSPRequest.setData(loaiSP);
        loaiSPRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = loaiSPService.xoa(loaiSPRequest.getId(), PermissionController.getInstance().getAuthentication());
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    LoaiSPController.getInstance().getLoaiSPs().remove(loaiSP);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(LoaiSPActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addDataToObject() {
        loaiSP.setName(ed_loaisp.getText().toString());
    }
}
