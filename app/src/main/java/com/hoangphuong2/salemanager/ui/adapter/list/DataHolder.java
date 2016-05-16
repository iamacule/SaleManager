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
    public ImageView imgCall;
    public ImageView imgSMS;
    public TextView txtAvatar;
    public TextView txtName;

    public DataHolder(View view) {
        super(view);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        imgCall = (ImageView) view.findViewById(R.id.imgCall);
        imgSMS = (ImageView) view.findViewById(R.id.imgSMS);
        txtAvatar = (TextView) view.findViewById(R.id.txtAvatar);
        txtName = (TextView) view.findViewById(R.id.txtName);
    }
}
