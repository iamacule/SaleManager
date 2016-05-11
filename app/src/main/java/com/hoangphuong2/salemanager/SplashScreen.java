package com.hoangphuong2.salemanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.ScreenUtil;

public class SplashScreen extends AppCompatActivity {
    private ImageView imgLogo;
    private float screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imgLogo = (ImageView)findViewById(R.id.imgLogo);
        screenWidth = ScreenUtil.getScreenWidth(getWindowManager());
        Bitmap logo = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(),
                R.drawable.sale_manager_icon),screenWidth/4);
        imgLogo.setImageBitmap(logo);
    }
}
