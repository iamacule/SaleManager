package com.hoangphuong2.salemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hoangphuong2.salemanager.R;

public abstract class BaseActivity extends AppCompatActivity implements IBasicCreateActivity {
    private Intent intent;

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

    protected void goToActivity(Class activity) {
        intent = new Intent(this, activity);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
