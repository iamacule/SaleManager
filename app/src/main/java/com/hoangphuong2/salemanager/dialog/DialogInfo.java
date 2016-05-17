package com.hoangphuong2.salemanager.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hoangphuong2.salemanager.R;

/**
 * Created by MrAn on 17-May-16.
 */
public class DialogInfo {
    public static TextView txtProgress;
    private static TextView txtMessage;
    public static AlertDialog dialog;


    public static void createDialog(Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_info, null);
        builder.setView(view);
        dialog = builder.create();
        txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        txtProgress = (TextView) view.findViewById(R.id.txtProgress);
        txtMessage.setText(message);
        txtProgress.setText("0/0");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    public static void show() {
        dialog.show();
    }

    public static void dismiss() {
        dialog.dismiss();
    }
}
