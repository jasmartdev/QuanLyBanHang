package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bk.danang.quanlybanhang.R;
import bk.danang.quanlybanhang.model.ChiTietDoanhThu;

/**
 * Created by voqua on 6/25/2016.
 */
public class DoanhThuAdapter extends BaseAdapter {

    private  Context context;
    private List<ChiTietDoanhThu> doanhThus;
    public DoanhThuAdapter(Context context, List<ChiTietDoanhThu> doanhThus){
        this.context = context;
        this.doanhThus = doanhThus;
    }

    public int getCount() {
        return doanhThus.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.doanhthu_layout, parent, false);
        }

        ChiTietDoanhThu doanhThu = doanhThus.get(position);

        setText(convertView, R.id.price_total,doanhThu.getTotal()+ context.getString(R.string.chung_tien));
        setText(convertView, R.id.tv_sanpham,doanhThu.getProduction());
        setText(convertView, R.id.tv_so_luong,doanhThu.getQuantity());
        setText(convertView, R.id.tv_nguoiban,doanhThu.getEmployee());
        setText(convertView, R.id.tv_ten_khach,doanhThu.getCustomer());
        setText(convertView, R.id.tv_giaban,doanhThu.getPrice() + context.getString(R.string.chung_tien));
        setText(convertView, R.id.tv_giamgia,doanhThu.getDiscount());

        return convertView;
    }

    private void setText(View view,  int id, Object value){
        ((TextView)view.findViewById(id)).setText(""+value);
    }
}
