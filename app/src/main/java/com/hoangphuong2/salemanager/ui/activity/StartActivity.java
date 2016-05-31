package com.hoangphuong2.salemanager.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.data.preferences.Preferences;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.ui.util.ResizeBitmap;
import com.hoangphuong2.salemanager.ui.util.ScreenUtil;

/**
 * Created by MrAn on 13-May-16.
 */
public class StartActivity extends BaseActivity {
    private ImageView imgLogo;
    private EditText etName;
    private LinearLayout inputName;
    private TextInputLayout hintName;
    private Button btnConfirm;
    private float screenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
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
        if (etName.getText().toString().trim().equals("")) {

            hintName.setError(null);
            hintName.setError(getString(R.string.name_emplty));
            etName.requestFocus();

        } else if (etName.getText().toString().trim().length() < 3) {

            hintName.setError(getString(R.string.min_lenge));
            etName.requestFocus();

        } else if (etName.getText().toString().trim().length() > 20) {

            hintName.setError(getString(R.string.max_lenge));
            etName.requestFocus();

        } else {
            Preferences.getInstance(getApplicationContext()).storeValue(Preferences.SALE_MANAGER_OWNER_NAME, etName.getText().toString());
            Boast.makeText(this, getString(R.string.toast_save_success)).show();
            Intent intent = new Intent(this, MainActivity.class);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void identifyLayout() {
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        inputName = (LinearLayout) findViewById(R.id.inputName);
        etName = (EditText) inputName.findViewById(R.id.et);
        hintName = (TextInputLayout) inputName.findViewById(R.id.hint);
        etName.setHint(getString(R.string.splash_screen_et_hint));
        btnConfirm = (Button) findViewById(R.id.btnComfirm);
    }

    @Override
    public void identityValue() {
        screenWidth = ScreenUtil.getScreenWidth(getWindowManager());
        Bitmap logo = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(),
                R.drawable.sale_manager_icon), screenWidth / 4);
        imgLogo.setImageBitmap(logo);
    }

    @Override
    public void identityAction() {
        setOnClick();
    }
}
