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
import bk.danang.quanlybanhang.NhanHieuActivity;
import bk.danang.quanlybanhang.model.NhanHieu;

public class NhanHieuAdapter  extends BaseAdapter {
    private List<NhanHieu> nhanHieus;
    private Context context;

    public NhanHieuAdapter(Context context, List<NhanHieu> nhanHieus) {
        this.context = context;
        this.nhanHieus = nhanHieus;
    }

    @Override
    public int getCount() {
        return nhanHieus.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanHieus.size();
    }

    @Override
    public long getItemId(int position) {
        return nhanHieus.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nhan_hieu_item, parent, false);
        }
        final NhanHieu nhanHieu = nhanHieus.get(position);
        TextView tv_nhan_hieu = (TextView) convertView.findViewById(R.id.tv_nhan_hieu);
        tv_nhan_hieu.setText(nhanHieu.getName());
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, NhanHieuActivity.class);
                intent.putExtra("object", nhanHieu.getId());
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }
}
