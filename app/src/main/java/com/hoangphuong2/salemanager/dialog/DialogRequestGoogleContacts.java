package com.hoangphuong2.salemanager.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.hoangphuong2.salemanager.R;

/**
 * Created by MrAn on 17-May-16.
 */
public class DialogRequestGoogleContacts {
    public static WebView webView;
    public static AlertDialog dialog;


    public static void createDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.auth_dialog, null);
        builder.setView(view);
        dialog = builder.create();
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    public static void show() {
        dialog.show();
    }
}
