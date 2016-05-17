package com.hoangphuong2.salemanager.dialog;

import android.app.Activity;

import com.hoangphuong2.salemanager.R;

/**
 * Created by MrAn on 17-May-16.
 */
public class PDialog {
    private static android.app.ProgressDialog progressDialog;

    public static void show(Activity activity, String message) {
        if (progressDialog == null) {
            progressDialog = new android.app.ProgressDialog(activity, R.style.ProgressDialog);
            progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            if (progressDialog != null && !progressDialog.isShowing())
                progressDialog.show();
        }
    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
