package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.NhomKHController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.NhomKH;
import bk.danang.quanlybanhang.model.NhomKHRequest;
import bk.danang.quanlybanhang.util.Util;
import bk.danang.quanlybanhang.webinterface.NhomKHService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class NhomKHActivity extends AppCompatActivity {
    private EditText ed_nhomkh, ed_giam_gia, ed_ghi_chu;
    private int id;
    NhomKH nhomKH = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomkh);
        ed_nhomkh = (EditText) findViewById(R.id.ed_nhomkh);
        ed_giam_gia = (EditText) findViewById(R.id.ed_giam_gia);
        ed_ghi_chu = (EditText) findViewById(R.id.ed_ghi_chu);
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
        if (nhomKH == null) {
            nhomKH = new NhomKH();
        }
        if (id != -1) {
            for (NhomKH nhomKH1 : NhomKHController.getInstance().getNhomKHs()) {
                if (nhomKH1.getId() == id) {
                    nhomKH = nhomKH1;
                    break;
                }
            }
            ed_nhomkh.setText(nhomKH.getName());
            ed_giam_gia.setText(Integer.toString(nhomKH.getDiscountPercent()));
            ed_ghi_chu.setText(nhomKH.getNote());
        }
    }

    public void Save(View view) {
        progressDialog.show();
        addDataToObject();
        NhomKHService nhomKHService = retrofit.create(NhomKHService.class);
        NhomKHRequest nhomKHRequest = new NhomKHRequest();
        nhomKHRequest.setData(nhomKH);
        nhomKHRequest.setId(nhomKH.getId());
        nhomKHRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = nhomKHService.sua(nhomKHRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(NhomKHActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<NhomKH> call = nhomKHService.them(nhomKHRequest);
            call.enqueue(new Callback<NhomKH>() {
                public void onResponse(Response<NhomKH> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    NhomKHController.getInstance().getNhomKHs().add(nhomKH);
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(NhomKHActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        NhomKHService nhomKHService = retrofit.create(NhomKHService.class);
        NhomKHRequest nhomKHRequest = new NhomKHRequest();
        nhomKHRequest.setData(nhomKH);
        nhomKHRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = nhomKHService.xoa(nhomKHRequest.getId(), PermissionController.getInstance().getAuthentication());
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    NhomKHController.getInstance().getNhomKHs().remove(nhomKH);
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(NhomKHActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addDataToObject() {
        nhomKH.setName(ed_nhomkh.getText().toString());
        nhomKH.setDiscountPercent(Util.GetNumber(ed_giam_gia));
        nhomKH.setNote(ed_ghi_chu.getText().toString());
    }
}
