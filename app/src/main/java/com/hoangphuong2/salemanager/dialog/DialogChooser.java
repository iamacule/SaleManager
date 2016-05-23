package com.hoangphuong2.salemanager.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hoangphuong2.salemanager.R;

/**
 * Created by MrAn on 23-May-16.
 */
public class DialogChooser {
    public static TextView btnCancel;
    public static TextView btnConfirm;
    private static TextView txtMessage;
    public static RadioGroup radioGroup;
    public static AlertDialog dialog;


    public static void createDialog(Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chooser, null);
        builder.setView(view);
        dialog = builder.create();
        txtMessage = (TextView) view.findViewById(R.id.tv_message);
        btnConfirm = (TextView) view.findViewById(R.id.btn_ok);
        btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        txtMessage.setText(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    public static void show() {
        dialog.show();
    }
}
