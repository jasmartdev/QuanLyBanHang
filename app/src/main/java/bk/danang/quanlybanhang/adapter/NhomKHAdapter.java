package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.NhomKHActivity;
import bk.danang.quanlybanhang.model.NhomKH;

public class NhomKHAdapter  extends BaseAdapter {
    private NhomKH[] nhomKHs;
    private Context context;

    public NhomKHAdapter(Context context, NhomKH[] nhomKHs) {
        this.context = context;
        this.nhomKHs = nhomKHs;
    }

    @Override
    public int getCount() {
        return nhomKHs.length;
    }

    @Override
    public Object getItem(int position) {
        return nhomKHs[position];
    }

    @Override
    public long getItemId(int position) {
        return nhomKHs[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nhomkh_item, parent, false);
        }
        final NhomKH nhomKH = nhomKHs[position];
        TextView tv_nhomkh = (TextView) convertView.findViewById(R.id.tv_nhomkh);
        tv_nhomkh.setText(nhomKH.getName());
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
