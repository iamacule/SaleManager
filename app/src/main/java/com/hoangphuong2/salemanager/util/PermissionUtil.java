package com.hoangphuong2.salemanager.util;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;

/**
 * Created by MrAn on 29-Apr-16.
 */
public class PermissionUtil {
    public static final int READ_CONTACTS = 1;
    public static boolean permissionReadContacts = false;

    public static void request(Activity activity, String permission, int idCallBack) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, idCallBack);
    }
}
