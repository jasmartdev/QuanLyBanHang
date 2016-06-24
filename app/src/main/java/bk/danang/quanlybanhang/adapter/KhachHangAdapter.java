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
import bk.danang.quanlybanhang.KhachHangActivity;
import bk.danang.quanlybanhang.model.KhachHang;

public class KhachHangAdapter extends BaseAdapter {
    private List<KhachHang> khachHangs;
    private Context context;

    public KhachHangAdapter(Context context, List<KhachHang> khachHangs) {
        this.context = context;
        this.khachHangs = khachHangs;
    }

    @Override
    public int getCount() {
        return khachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return khachHangs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.khach_hang_item, parent, false);
        }
        final KhachHang khachHang = khachHangs.get(position);
        TextView tv_ten_khach = (TextView) convertView.findViewById(R.id.tv_ten_khach);
        TextView tv_gioi_tinh = (TextView) convertView.findViewById(R.id.tv_gioi_tinh);
        TextView tv_dia_chi = (TextView) convertView.findViewById(R.id.tv_dia_chi);
        TextView tv_nhom = (TextView) convertView.findViewById(R.id.tv_nhom);
        tv_ten_khach.setText(khachHang.getName());
        tv_gioi_tinh.setText(khachHang.getGender());
        tv_dia_chi.setText(khachHang.getAddress());
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, KhachHangActivity.class);
                intent.putExtra("object", khachHang.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
