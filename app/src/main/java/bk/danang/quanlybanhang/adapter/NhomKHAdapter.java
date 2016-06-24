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
import bk.danang.quanlybanhang.NhomKHActivity;
import bk.danang.quanlybanhang.model.NhomKH;

public class NhomKHAdapter extends BaseAdapter {
    private List<NhomKH> nhomKHs;
    private Context context;

    public NhomKHAdapter(Context context, List<NhomKH> nhomKHs) {
        this.context = context;
        this.nhomKHs = nhomKHs;
    }

    @Override
    public int getCount() {
        return nhomKHs.size();
    }

    @Override
    public Object getItem(int position) {
        return nhomKHs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return nhomKHs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nhomkh_item, parent, false);
        }
        final NhomKH nhomKH = nhomKHs.get(position);
        TextView tv_nhomkh = (TextView) convertView.findViewById(R.id.tv_nhomkh);
        TextView tv_ghi_chu = (TextView) convertView.findViewById(R.id.tv_ghi_chu);
        tv_nhomkh.setText(nhomKH.getName());
        tv_ghi_chu.setText(nhomKH.getNote());
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, NhomKHActivity.class);
                intent.putExtra("object", nhomKH.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
