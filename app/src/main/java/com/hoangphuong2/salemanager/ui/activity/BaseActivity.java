package com.hoangphuong2.salemanager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements IBasicCreateActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        identifyLayout();
        identityValue();
        identityAction();
    }

    protected int getLayout() {
        return -1;
    }
}
