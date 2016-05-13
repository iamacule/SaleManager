package com.hoangphuong2.salemanager.ui.adapter.list;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.model.Private;

import java.util.List;

/**
 * Created by MrAn on 13-May-16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataHolder> {
    private List<Private> listData;
    private Bitmap bitmap;

    public DataAdapter(List<Private> listData, Bitmap bitmap) {
        this.listData = listData;
        this.bitmap = bitmap;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data, parent, false);

        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    private Private getItem(int pos) {
        return listData.get(pos);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        final Private aPrivate = getItem(position);
        holder.imgAvatar.setImageBitmap(bitmap);
        holder.txtAvatar.setText(Character.toString(aPrivate.getName().charAt(0)).toUpperCase());
        holder.txtName.setText(aPrivate.getName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
