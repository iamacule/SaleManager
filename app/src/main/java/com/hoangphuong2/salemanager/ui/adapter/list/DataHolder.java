package com.hoangphuong2.salemanager.ui.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangphuong2.salemanager.R;

/**
 * Created by MrAn PC on 21-Feb-16.
 */
public class DataHolder extends RecyclerView.ViewHolder {
    public ImageView imgAvatar;
    public TextView txtAvatar;
    public TextView txtName;
    public CheckBox checkBox;

    public DataHolder(View view) {
        super(view);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        txtAvatar = (TextView) view.findViewById(R.id.txtAvatar);
        txtName = (TextView) view.findViewById(R.id.txtName);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
    }
}
