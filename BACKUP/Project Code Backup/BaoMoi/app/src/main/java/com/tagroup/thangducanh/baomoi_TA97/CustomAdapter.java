package com.tagroup.thangducanh.baomoi_TA97;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by thangducanh on 21/11/2017.
 */

public class CustomAdapter extends ArrayAdapter<DocBao> {

    public CustomAdapter(Context context, int resource, List<DocBao> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.dong_layout_listview, null);
        }
        DocBao p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle =  view.findViewById(R.id.textviewTitle);
            txtTitle.setText(p.getTitle());
            ImageView imageView = view.findViewById(R.id.imageView);
            Picasso.with(getContext()).load(p.getImage()).into(imageView);

        }
        return view;
    }

}
