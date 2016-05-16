package com.hoangphuong2.salemanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hoangphuong2.salemanager.data.preferences.Preferences;
import com.hoangphuong2.salemanager.data.sqlite.Database;
import com.hoangphuong2.salemanager.ui.activity.MainActivity;
import com.hoangphuong2.salemanager.ui.activity.StartActivity;
import com.hoangphuong2.salemanager.util.DataUtil;
import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.ScreenUtil;
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
        DataUtil.screenWidth = ScreenUtil.getScreenWidth(getWindowManager());
        DataUtil.redCircle = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(), R.drawable.red_circle), DataUtil.screenWidth / 8);
        DataUtil.imgSMS = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(), R.drawable.sms), DataUtil.screenWidth / 14);
        DataUtil.imgCall = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(), R.drawable.call), DataUtil.screenWidth / 12);
        Database.setUp(getApplicationContext());
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
