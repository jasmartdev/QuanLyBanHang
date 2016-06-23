package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.NhanVienActivity;
import bk.danang.quanlybanhang.model.NhanVien;

public class NhanVienAdapter  extends BaseAdapter {
    private NhanVien[] nhanViens;
    private Context context;

    public NhanVienAdapter(Context context, NhanVien[] nhanViens) {
        this.context = context;
        this.nhanViens = nhanViens;
    }

    @Override
    public int getCount() {
        return nhanViens.length;
    }

    @Override
    public Object getItem(int position) {
        return nhanViens[position];
    }

    @Override
    public long getItemId(int position) {
        return nhanViens[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nhan_hieu_item, parent, false);
        }
        final NhanVien nhanVien = nhanViens[position];
        TextView tv_ten_sp = (TextView) convertView.findViewById(R.id.tv_ten_sp);
        TextView tv_tri_gia = (TextView) convertView.findViewById(R.id.tv_tri_gia);
        TextView tv_so_luong = (TextView) convertView.findViewById(R.id.tv_so_luong);
        tv_ten_sp.setText(nhanVien.getName());
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
