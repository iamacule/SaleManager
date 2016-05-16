package com.hoangphuong2.salemanager.ui.adapter.list;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.data.sqlite.Database;
import com.hoangphuong2.salemanager.helper.PhoneHelper;
import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Private;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.util.DataUtil;

import java.util.List;

/**
 * Created by MrAn on 13-May-16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataHolder> {
    private List<Private> listData;
    private Activity activity;
    private String phoneNumber;
    private PhoneHelper phoneHelper;

    public DataAdapter(Activity activity, List<Private> listData) {
        this.activity = activity;
        this.listData = listData;
        phoneHelper = new PhoneHelper(activity);
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
        holder.imgAvatar.setImageBitmap(DataUtil.redCircle);
        holder.imgSMS.setImageBitmap(DataUtil.imgSMS);
        holder.imgCall.setImageBitmap(DataUtil.imgCall);
        holder.txtAvatar.setText(Character.toString(aPrivate.name.charAt(0)).toUpperCase());
        holder.txtName.setText(aPrivate.name);
        setOnClick(holder, aPrivate);
    }

    private void setOnClick(final DataHolder holder, final Private aPrivate) {
        OnSingleClickListener click = new OnSingleClickListener(100) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.imgSMS:
                        action(aPrivate, false);
                        break;
                    case R.id.imgCall:
                        action(aPrivate, true);
                        break;
                }
            }
        };

        holder.imgSMS.setOnClickListener(click);
        holder.imgCall.setOnClickListener(click);
    }

    private void action(Private aPrivate, boolean isCall) {
        Database.getInstance().open();
        List<Phone> listPhone = Database.getInstance().getAllPhoneOfPerson(aPrivate.idPrivate);
        Database.getInstance().close();
        if (listPhone.size() > 0) {
            if (listPhone.size() == 1) {
                phoneNumber = listPhone.get(0).number;
                if (!isCall) {
                    phoneHelper.message(phoneNumber, "");
                } else {
                    phoneHelper.dial(phoneNumber);
                }
            }
        } else {
            Boast.makeText(activity, activity.getString(R.string.private_no_phone)).show();
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
