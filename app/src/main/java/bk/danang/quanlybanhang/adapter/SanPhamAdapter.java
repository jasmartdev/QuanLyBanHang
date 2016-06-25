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
import bk.danang.quanlybanhang.SanPhamActivity;
import bk.danang.quanlybanhang.control.ImageUrlView;
import bk.danang.quanlybanhang.model.SanPham;

public class SanPhamAdapter extends BaseAdapter {
    private List<SanPham> sanPhams;
    private Context context;

    public SanPhamAdapter(Context context, List<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
    }

    @Override
    public Object getItem(int position) {
        return sanPhams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sanPhams.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.san_pham_item, parent, false);
        }
        final SanPham sanPham = sanPhams.get(position);
        TextView tv_ten_sp = (TextView) convertView.findViewById(R.id.tv_ten_sp);
        TextView tv_tri_gia = (TextView) convertView.findViewById(R.id.tv_tri_gia);
        TextView tv_so_luong = (TextView) convertView.findViewById(R.id.tv_so_luong);
        ImageUrlView img = (ImageUrlView) convertView.findViewById(R.id.sanpham_photo);

        tv_ten_sp.setText(sanPham.getName());
        tv_tri_gia.setText(context.getString(R.string.chung_gia) + ":" + Integer.toString(sanPham.getOriginPrice()));
        tv_so_luong.setText(context.getString(R.string.chung_so_luong) + ":" + Integer.toString(sanPham.getQuantity()));
        img.setUrl(sanPham.getImageurl());
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
