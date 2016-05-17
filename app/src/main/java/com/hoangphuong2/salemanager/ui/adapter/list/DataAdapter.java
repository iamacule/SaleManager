package com.hoangphuong2.salemanager.ui.adapter.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.helper.PhoneHelper;
import com.hoangphuong2.salemanager.model.Person;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.util.DataUtil;

import java.util.List;

/**
 * Created by MrAn on 13-May-16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataHolder> {
    private List<Person> listData;
    private Activity activity;
    private String phoneNumber;
    private PhoneHelper phoneHelper;

    public DataAdapter(Activity activity, List<Person> listData) {
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

    private Person getItem(int pos) {
        return listData.get(pos);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        final Person aPerson = getItem(position);
        holder.imgAvatar.setImageBitmap(DataUtil.redCircle);
        holder.imgSMS.setImageBitmap(DataUtil.imgSMS);
        holder.imgCall.setImageBitmap(DataUtil.imgCall);
        holder.txtAvatar.setText(Character.toString(aPerson.name.charAt(0)).toUpperCase());
        holder.txtName.setText(aPerson.name);
        setOnClick(holder, aPerson);
    }

    private void setOnClick(final DataHolder holder, final Person aPerson) {
        OnSingleClickListener click = new OnSingleClickListener(100) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.imgSMS:
                        action(aPerson, false);
                        break;
                    case R.id.imgCall:
                        action(aPerson, true);
                        break;
                }
            }
        };

        holder.imgSMS.setOnClickListener(click);
        holder.imgCall.setOnClickListener(click);
    }

    private void action(Person aPerson, boolean isCall) {

        if (aPerson.listPhone.size() > 0) {
            if (aPerson.listPhone.size() == 1) {
                phoneNumber = aPerson.listPhone.get(0).number;
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
