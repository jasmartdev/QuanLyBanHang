package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.NhanVienActivity;
import bk.danang.quanlybanhang.model.NhanVien;
import bk.danang.quanlybanhang.util.Util;

public class NhanVienAdapter extends BaseAdapter {
    private List<NhanVien> nhanViens;
    private Context context;

    public NhanVienAdapter(Context context, List<NhanVien> nhanViens) {
        this.context = context;
        this.nhanViens = nhanViens;
    }

    @Override
    public int getCount() {
        return nhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return nhanViens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nhan_vien_item, parent, false);
        }
        final NhanVien nhanVien = nhanViens.get(position);
        TextView tvTenNv = (TextView) convertView.findViewById(R.id.tv_ten_nv);
        TextView tvGioiTinh = (TextView) convertView.findViewById(R.id.tv_gioi_tinh);
        TextView tvDiaChi = (TextView) convertView.findViewById(R.id.tv_dia_chi);
        TextView tvCongViec = (TextView) convertView.findViewById(R.id.tv_cong_viec);

        tvTenNv.setText(nhanVien.getName());
        tvGioiTinh.setText(Util.ConvertGender(nhanVien.getGender()));
        tvDiaChi.setText("Địa chỉ:" + nhanVien.getAddress());
        tvCongViec.setText("Công việc:" + nhanVien.getJobTitle());

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, NhanVienActivity.class);
                intent.putExtra("object", nhanVien.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
