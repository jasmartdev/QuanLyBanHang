package bk.danang.quanlybanhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import bk.danang.quanlybanhang.adapter.SpinnerAdapter;
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
    private EditText ed_sanpham, ed_so_luong, ed_gia_goc, ed_gia_vip, ed_gia_si, ed_gia_le;
    private int id;
    SanPham sanPham = null;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstant.WEB_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

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
        Intent intent = getIntent();
        id = intent.getIntExtra("object", -1);
        if (id == -1 || !PermissionController.getInstance().getIsAdmin()) {
            ((Button) findViewById(R.id.btn_delete)).setVisibility(View.INVISIBLE);
        }
        setObject();
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
        }
        List<LoaiSP> loaiSPList = LoaiSPController.getInstance().getLoaiSPs();
        List<NhanHieu> nhanHieuList = NhanHieuController.getInstance().getNhanHieus();
        spn_loaisp.setAdapter(new SpinnerAdapter<LoaiSP>(this, android.R.layout.simple_spinner_dropdown_item, loaiSPList));
        int loaispIndex = 0;
        int nhanhieuIndex = 0;
        if (id != -1) {
            for (int i = 1; i < loaiSPList.size(); i++) {
                if (loaiSPList.get(i).getId() == sanPham.getCategoryId()) {
                    loaispIndex = i;
                    break;
                }
            }
            for (int i = 1; i < loaiSPList.size(); i++) {
                if (loaiSPList.get(i).getId() == sanPham.getCategoryId()) {
                    nhanhieuIndex = i;
                    break;
                }
            }
        }
        spn_loaisp.setSelection(loaispIndex);
        spn_hieu.setAdapter(new SpinnerAdapter<NhanHieu>(this, android.R.layout.simple_spinner_dropdown_item, nhanHieuList));
        spn_hieu.setSelection(nhanhieuIndex);
    }

    public void Save(View view) {
        addDataToObject();
        SanPhamService sanPhamService = retrofit.create(SanPhamService.class);
        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setData(sanPham);
        if (id != -1) {
            final Call<Object> call = sanPhamService.sua(sanPhamRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    startActivity(new Intent(SanPhamActivity.this, QuanLySanPhamActivity.class));
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(SanPhamActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final Call<SanPham> call = sanPhamService.them(sanPhamRequest);
            call.enqueue(new Callback<SanPham>() {
                public void onResponse(Response<SanPham> response, Retrofit retrofit) {
                    SanPhamController.getInstance().getSanPhams().add(sanPham);
                    startActivity(new Intent(SanPhamActivity.this, QuanLySanPhamActivity.class));
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(SanPhamActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
        this.finish();
    }

    public void Delete(View view) {
        SanPhamService sanPhamService = retrofit.create(SanPhamService.class);
        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setData(sanPham);
        if (id != -1) {
            final Call<Object> call = sanPhamService.xoa(sanPhamRequest);
            call.enqueue(new Callback<Object>() {
                public void onResponse(Response<Object> response, Retrofit retrofit) {
                    System.out.println("respone " + response.body());
                    startActivity(new Intent(SanPhamActivity.this, QuanLySanPhamActivity.class));
                }

                public void onFailure(Throwable t) {
                    Toast.makeText(SanPhamActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
        this.finish();
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
    }
}
