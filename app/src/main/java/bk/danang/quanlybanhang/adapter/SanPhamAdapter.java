package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.SanPhamActivity;
import bk.danang.quanlybanhang.model.SanPham;

public class SanPhamAdapter extends BaseAdapter {
    private SanPham[] sanPhams;
    private Context context;

    public SanPhamAdapter(Context context, SanPham[] sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    @Override
    public int getCount() {
        return sanPhams.length;
    }

    @Override
    public Object getItem(int position) {
        return sanPhams[position];
    }

    @Override
    public long getItemId(int position) {
        return sanPhams[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.san_pham_item, parent, false);
        }
        final SanPham sanPham = sanPhams[position];
        TextView tv_ten_sp = (TextView) convertView.findViewById(R.id.tv_ten_sp);
        TextView tv_tri_gia = (TextView) convertView.findViewById(R.id.tv_tri_gia);
        TextView tv_so_luong = (TextView) convertView.findViewById(R.id.tv_so_luong);
        tv_ten_sp.setText(sanPham.getName());
        tv_tri_gia.setText(Integer.toString(sanPham.getOriginPrice()));
        tv_so_luong.setText(Integer.toString(sanPham.getQuantity()));
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, SanPhamActivity.class);
                intent.putExtra("object", sanPham.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
