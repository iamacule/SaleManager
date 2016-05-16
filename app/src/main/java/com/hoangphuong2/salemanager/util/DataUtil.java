package com.hoangphuong2.salemanager.util;

import android.graphics.Bitmap;

import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Private;

/**
 * Created by MrAn on 16-May-16.
 */
public class DataUtil {
    public static Bitmap redCircle;
    public static Bitmap imgCall;
    public static Bitmap imgSMS;
    public static float screenWidth;
    public static Private privateData;
    public static Phone phoneData;
    public static boolean needAddNew = false;

    public static final int PRIVATE = 0;
    public static final int COMPANY = 1;
    public static final int BILL = 2;
    public static int current_fragment;
}
