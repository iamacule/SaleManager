package com.hoangphuong2.salemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hoangphuong2.salemanager.data.preferences.Preferences;
import com.hoangphuong2.salemanager.ui.activity.MainActivity;
import com.hoangphuong2.salemanager.ui.activity.StartActivity;
import com.hoangphuong2.salemanager.util.Tag;

public class SplashScreen extends AppCompatActivity {
    private String ownerName;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initValue();
        initActivity();
    }

    private void initValue() {
        ownerName = Preferences.getInstance(getApplicationContext()).getStringValue(Preferences.SALE_MANAGER_OWNER_NAME);
    }

    private void initActivity() {
        if(ownerName!=null){
            intent = new Intent(this, MainActivity.class);
            Log.d(Tag.SplashScreen,"Name is config,go to Main");
        }else {
            intent = new Intent(this, StartActivity.class);
            Log.d(Tag.SplashScreen,"Name is not config,go to Start");
        }
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        startActivity(intent);
    }
}
