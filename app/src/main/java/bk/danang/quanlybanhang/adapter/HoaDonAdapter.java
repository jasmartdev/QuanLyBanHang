package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.HoaDonActivity;
import bk.danang.quanlybanhang.model.HoaDon;

/**
 * Created by Admin on 6/23/2016.
 */
public class HoaDonAdapter extends BaseAdapter {
    private HoaDon[] hoaDons;
    private Context context;

    public HoaDonAdapter(Context context, HoaDon[] hoaDons) {
        this.context = context;
        this.hoaDons = hoaDons;
    }

    @Override
    public int getCount() {
        return hoaDons.length;
    }

    @Override
    public Object getItem(int position) {
        return hoaDons[position];
    }

    @Override
    public long getItemId(int position) {
        return hoaDons[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hoa_don_item, parent, false);
        }
        final HoaDon hoaDon = hoaDons[position];
        TextView tv_ten_sp = (TextView) convertView.findViewById(R.id.tv_ten_sp);
        tv_ten_sp.setText(hoaDon.getName());
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