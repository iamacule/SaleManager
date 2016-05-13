package com.hoangphuong2.salemanager.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.data.preferences.Preferences;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.ScreenUtil;

/**
 * Created by MrAn on 13-May-16.
 */
public class StartActivity extends AppCompatActivity {
    private ImageView imgLogo;
    private EditText etName;
    private Button btnConfirm;
    private float screenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initLayout();
        setOnClick();
    }

    private void setOnClick() {
        OnSingleClickListener click = new OnSingleClickListener(200) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.btnComfirm:
                        save();
                        break;
                }
            }
        };
        btnConfirm.setOnClickListener(click);
    }

    private void save() {
        Preferences.getInstance(getApplicationContext()).storeValue(Preferences.SALE_MANAGER_OWNER_NAME, etName.getText().toString());
        Boast.makeText(this, getString(R.string.toast_save_success)).show();
        Intent intent = new Intent(this,MainActivity.class);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        startActivity(intent);
    }

    private void initLayout() {
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        etName = (EditText) findViewById(R.id.etName);
        btnConfirm = (Button) findViewById(R.id.btnComfirm);
        screenWidth = ScreenUtil.getScreenWidth(getWindowManager());
        Bitmap logo = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(),
                R.drawable.sale_manager_icon), screenWidth / 4);
        imgLogo.setImageBitmap(logo);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
