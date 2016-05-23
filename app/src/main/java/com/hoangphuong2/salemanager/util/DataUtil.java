package com.hoangphuong2.salemanager.util;

import android.graphics.Bitmap;

import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrAn on 16-May-16.
 */
public class DataUtil {
    public static String PHONE_MOBILE;
    public static String PHONE_HOME;
    public static String PHONE_WORK;
    public static String PHONE_MORE;
    public static List<String> listPhoneNote;
    public static int PHONE_MOBILE_INT = 0;
    public static int PHONE_HOME_INT = 1;
    public static int PHONE_WORK_INT = 2;
    public static int PHONE_MORE_INT = 3;
    public static Bitmap redCircle;
    public static Bitmap imgCall;
    public static Bitmap imgSMS;
    public static float screenWidth;
    public static Person personData;
    public static Phone phoneData;
    public static boolean needAddNew = false;
    public static boolean needRefresh = false;

    public static final int PRIVATE = 0;
    public static final int COMPANY = 1;
    public static final int BILL = 2;
    public static int current_fragment;

    public static void createListPhoneNote(){
        listPhoneNote = new ArrayList<>();
        listPhoneNote.add(PHONE_MOBILE);
        listPhoneNote.add(PHONE_HOME);
        listPhoneNote.add(PHONE_WORK);
        listPhoneNote.add(PHONE_MORE);
    }
}
