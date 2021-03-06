package com.hoangphuong2.salemanager.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by An.Pham on 11/05/2016
 */
public final class Preferences {
    private SharedPreferences pref;
    private Editor editor;
    private static Preferences instance;

    public static final String SALE_MANAGER_PREFERENCES = "SALE_MANAGER_PREFERENCES";
    public static final String SALE_MANAGER_OWNER_NAME = "SALE_MANAGER_OWNER_NAME";

    private Preferences(Context context, String preferencesName) {
        pref = context.getSharedPreferences(preferencesName, context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static Preferences getInstance(Context context){
        if(instance==null){
            instance = new Preferences(context,SALE_MANAGER_PREFERENCES);
        }
        return instance;
    }

    public void storeValue(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        }
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        if (value instanceof Long) {
            editor.putLong(key, (long) value);
        }
        editor.commit();
        Log.d("Preferences store","Key("+key+") , Value("+value+")");
    }

    public int getIntValue(String key) {
        return pref.getInt(key, -1);
    }

    public boolean getBooleanValue(String key) {
        return pref.getBoolean(key, false);
    }

    public String getStringValue(String key) {
        return pref.getString(key,null);
    }
}
