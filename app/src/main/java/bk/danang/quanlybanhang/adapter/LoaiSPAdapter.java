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
import bk.danang.quanlybanhang.LoaiSPActivity;
import bk.danang.quanlybanhang.model.LoaiSP;

/**
 * Created by Admin on 6/23/2016.
 */
public class LoaiSPAdapter  extends BaseAdapter {
    private List<LoaiSP> loaiSPs;
    private Context context;

    public LoaiSPAdapter(Context context, List<LoaiSP> loaiSPs) {
        this.context = context;
        this.loaiSPs = loaiSPs;
    }

    @Override
    public int getCount() {
        return loaiSPs!=null? loaiSPs.size():0;
    }

    @Override
    public Object getItem(int position) {
        return loaiSPs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiSPs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.loaisp_item, parent, false);
        }
        final LoaiSP loaiSP = loaiSPs.get(position);
        TextView tv_ten_sp = (TextView) convertView.findViewById(R.id.tv_loaisp);
        tv_ten_sp.setText(loaiSP.getName());
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, LoaiSPActivity.class);
                intent.putExtra("object", loaiSP.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
