package bk.danang.quanlybanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter<T> extends ArrayAdapter<T> {
    private Context context;

    public SpinnerAdapter(Context context, int textViewResourceId, List<T> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        T object = getItem(position);
        if (object != null) {
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(object.toString());
        }
        return convertView;
    }
}
