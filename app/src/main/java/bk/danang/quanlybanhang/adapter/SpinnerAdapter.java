package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public abstract class SpinnerAdapter<T> extends ArrayAdapter<T> {
    private Context context;

    public SpinnerAdapter(Context context, int textViewResourceId, List<T> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
    }

    public abstract void drawText(TextView textView, T object);

    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("position " + position + "convertView " + convertView);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        T object = getItem(position);
        if (object != null) {
            System.out.println("position " + position + "convertView " + convertView + "drawwwwwwwwwwwwww");
            drawText(((TextView) convertView.findViewById(android.R.id.text1)), object);
        }
        return convertView;
    }
}
