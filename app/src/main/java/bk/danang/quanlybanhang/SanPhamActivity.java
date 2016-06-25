package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import bk.danang.quanlybanhang.control.ImageUrlView;
import bk.danang.quanlybanhang.controller.LoaiSPController;
import bk.danang.quanlybanhang.controller.NhanHieuController;
import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.SanPham;
import bk.danang.quanlybanhang.model.SanPhamRequest;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import bk.danang.quanlybanhang.webinterface.SanPhamService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SanPhamActivity extends AppCompatActivity {
    private Spinner spn_loaisp, spn_hieu;
    private EditText ed_sanpham, ed_so_luong, ed_gia_goc, ed_gia_vip, ed_gia_si, ed_gia_le, ed_image_url;
    private ImageUrlView imgPhoto;
    private int id;
    SanPham sanPham = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        spn_loaisp = (Spinner) findViewById(R.id.spn_loaisp);
        spn_hieu = (Spinner) findViewById(R.id.spn_hieu);
        ed_sanpham = (EditText) findViewById(R.id.ed_sanpham);
        ed_so_luong = (EditText) findViewById(R.id.ed_so_luong);
        ed_gia_goc = (EditText) findViewById(R.id.ed_gia_goc);
        ed_gia_vip = (EditText) findViewById(R.id.ed_gia_vip);
        ed_gia_si = (EditText) findViewById(R.id.ed_gia_si);
        ed_gia_le = (EditText) findViewById(R.id.ed_gia_le);
        ed_image_url = (EditText) findViewById(R.id.ed_image_url);
        imgPhoto = (ImageUrlView)findViewById(R.id.sanpham_photo);

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
        if (sanPham == null) {
            sanPham = new SanPham();
        }
        if (id != -1) {
            for (SanPham sanPham1 : SanPhamController.getInstance().getSanPhams()) {
                if (sanPham1.getId() == id) {
                    sanPham = sanPham1;
                    break;
                }
            }
            ed_sanpham.setText(sanPham.getName());
            ed_so_luong.setText(Integer.toString(sanPham.getQuantity()));
            ed_gia_goc.setText(Integer.toString(sanPham.getOriginPrice()));
            ed_gia_vip.setText(Integer.toString(sanPham.getVipPrice()));
            ed_gia_si.setText(Integer.toString(sanPham.getWholesalePrice()));
            ed_gia_le.setText(Integer.toString(sanPham.getRetailPrice()));
            ed_image_url.setText(sanPham.getImageurl());
            imgPhoto.setUrl(sanPham.getImageurl());
            imgPhoto.setVisibility(View.VISIBLE);
        }


        spn_loaisp.setAdapter(createArrayAdapter(LoaiSPController.getInstance().getLoaiSPs()));
        spn_hieu.setAdapter(createArrayAdapter(NhanHieuController.getInstance().getNhanHieus()));

        spn_loaisp.setSelection(LoaiSPController.getInstance().getIndexOfId(sanPham.getCategoryId()));
        spn_hieu.setSelection(NhanHieuController.getInstance().getIndexOfId(sanPham.getBrandId()));
    }

    private <T> ArrayAdapter<T> createArrayAdapter(List<T> data){
        return new ArrayAdapter<T>(this,android.R.layout.simple_spinner_dropdown_item, data);
    }

    private <T> ArrayAdapter<T> createArrayAdapter(T[] data){
        return new ArrayAdapter<T>(this,android.R.layout.simple_spinner_dropdown_item, data);
    }

    public void Save(View view) {
        progressDialog.show();
        addDataToObject();
        SanPhamService sanPhamService = retrofit.create(SanPhamService.class);
        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setData(sanPham);
        sanPhamRequest.setId(sanPham.getId());
        sanPhamRequest.setAuthentication(PermissionController.getInstance().getAuthentication());

        if (id != -1) {
            final Call<Object> call = sanPhamService.sua(sanPhamRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    finish();
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(SanPhamActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<SanPham> call = sanPhamService.them(sanPhamRequest);
            call.enqueue(new Callback<SanPham>() {
                public void onResponse(Response<SanPham> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    SanPhamController.getInstance().getSanPhams().add(sanPham);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(SanPhamActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Delete(View view) {
        progressDialog.show();
        SanPhamService sanPhamService = retrofit.create(SanPhamService.class);
        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setData(sanPham);
        sanPhamRequest.setId(sanPham.getId());
        sanPhamRequest.setAuthentication(PermissionController.getInstance().getAuthentication());
        if (id != -1) {
            final Call<Object> call = sanPhamService.xoa(id, PermissionController.getInstance().getAuthentication());
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    progressDialog.dismiss();
                    SanPhamController.getInstance().getSanPhams().remove(sanPham);
                    finish();
                }

                public void onFailure(Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(SanPhamActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addDataToObject() {
        sanPham.setName(ed_sanpham.getText().toString());
        sanPham.setCategoryId(((LoaiSP) spn_loaisp.getSelectedItem()).getId());
        sanPham.setBrandId(((NhanHieu) spn_hieu.getSelectedItem()).getId());
        sanPham.setQuantity(Integer.parseInt(ed_so_luong.getText().toString()));
        sanPham.setOriginPrice(Integer.parseInt(ed_gia_goc.getText().toString()));
        sanPham.setVipPrice(Integer.parseInt(ed_gia_vip.getText().toString()));
        sanPham.setWholesalePrice(Integer.parseInt(ed_gia_si.getText().toString()));
        sanPham.setRetailPrice(Integer.parseInt(ed_gia_le.getText().toString()));
        sanPham.setImageurl(ed_image_url.getText().toString());
    }
}
