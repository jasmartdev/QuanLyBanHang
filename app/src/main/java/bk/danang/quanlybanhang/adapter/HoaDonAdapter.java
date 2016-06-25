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
import bk.danang.quanlybanhang.HoaDonActivity;
import bk.danang.quanlybanhang.control.ImageUrlView;
import bk.danang.quanlybanhang.controller.NhanVienController;
import bk.danang.quanlybanhang.controller.SanPhamController;
import bk.danang.quanlybanhang.model.HoaDon;

public class HoaDonAdapter extends BaseAdapter {
    private List<HoaDon> hoaDons;
    private Context context;

    public HoaDonAdapter(Context context, List<HoaDon> hoaDons) {
        this.context = context;
        this.hoaDons = hoaDons;
    }

    @Override
    public int getCount() {
        return hoaDons.size();
    }

    @Override
    public Object getItem(int position) {
        return hoaDons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return hoaDons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hoa_don_item, parent, false);
        }
        final HoaDon hoaDon = hoaDons.get(position);
        TextView tv_ten_sp = (TextView) convertView.findViewById(R.id.tv_ten_sp);
        TextView tv_tri_gia = (TextView) convertView.findViewById(R.id.tv_tri_gia);
        TextView tv_so_luong = (TextView) convertView.findViewById(R.id.tv_so_luong);
        TextView tv_ten_nv = (TextView) convertView.findViewById(R.id.tv_ten_nv);


        tv_ten_nv.setText(NhanVienController.getInstance().findById(hoaDon.getEmployeeId())+"");
        tv_tri_gia.setText(hoaDon.getPrice()+"");
        tv_ten_sp.setText(SanPhamController.getInstance().findById(hoaDon.getProductionId())+"");

        tv_so_luong.setText(Integer.toString(hoaDon.getQuantity()));
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, HoaDonActivity.class);
                intent.putExtra("object", hoaDon.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
