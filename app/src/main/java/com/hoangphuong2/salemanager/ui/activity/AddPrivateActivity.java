package com.hoangphuong2.salemanager.ui.activity;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.data.sqlite.Database;
import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Private;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.ui.util.AnimationUtil;
import com.hoangphuong2.salemanager.util.DataUtil;
import com.hoangphuong2.salemanager.util.ResizeBitmap;

import java.util.regex.Pattern;

/**
 * Created by MrAn on 16-May-16.
 */
public class AddPrivateActivity extends AppCompatActivity {
    private final int MALE = 1;
    private final int FEMALE = 0;
    private int sex = MALE;
    private ImageView imgAdd;
    private int addCount = 0;
    private LinearLayout lnMorePhone2;
    private LinearLayout lnMorePhone3;
    private LinearLayout lnMorePhone4;
    private LinearLayout lnMorePhone5;
    private EditText etPhone;
    private EditText etName;
    private EditText etAddress;
    private EditText etMail;
    private EditText etNote;
    private EditText etPhone2;
    private EditText etPhone3;
    private EditText etPhone4;
    private EditText etPhone5;
    private ImageView imgLogo;
    private Bitmap bpAdd;
    private Button btnSave;
    private RadioButton radioMale;
    private RadioButton radioFemale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_private);
        initLayout();
    }

    private void initLayout() {
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etName = (EditText) findViewById(R.id.etName);
        etNote = (EditText) findViewById(R.id.etNote);
        etMail = (EditText) findViewById(R.id.etMail);
        btnSave = (Button) findViewById(R.id.btnSave);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);

        prepareValue();
        setOnClick();
    }

    private void prepareValue() {
        bpAdd = ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(), R.drawable.add_icon_green), DataUtil.screenWidth / 16);
        imgAdd.setImageBitmap(bpAdd);
        imgLogo.setImageBitmap(ResizeBitmap.resize(BitmapFactory.decodeResource(getResources(), R.drawable.add_icon_title), DataUtil.screenWidth / 4));
    }

    @Override
    protected void onPause() {
        super.onPause();
        addCount = 0;
    }

    private void setOnClick() {
        OnSingleClickListener click = new OnSingleClickListener(200) {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.imgAdd:
                        addPhoneButton();
                        break;
                    case R.id.btnSave:
                        saveData();
                        break;
                    case R.id.radioMale:
                        sex = MALE;
                        break;
                    case R.id.radioFemale:
                        sex = FEMALE;
                        break;
                }
            }
        };
        imgAdd.setOnClickListener(click);
        btnSave.setOnClickListener(click);
        radioMale.setOnClickListener(click);
        radioFemale.setOnClickListener(click);
    }

    private void saveData() {
        boolean canSave = true;
        if (etName.getText() == null || etName.getText().equals(""))
            canSave = false;
        if (etAddress.getText() == null || etAddress.getText().equals(""))
            canSave = false;
        if (etMail.getText() == null || etMail.getText().equals(""))
            canSave = false;
        if (etPhone.getText() == null || etPhone.getText().equals(""))
            canSave = false;
        if (!etPhone.getText().toString().matches(String.valueOf(Patterns.PHONE)))
            canSave = false;
        if (!etMail.getText().toString().matches(String.valueOf(Patterns.EMAIL_ADDRESS)))
            canSave = false;

        if (canSave) {
            Database.getInstance().open();
            //Create and insert Private
            DataUtil.privateData = new Private();
            DataUtil.privateData.name = etName.getText().toString().trim();
            DataUtil.privateData.address = etAddress.getText().toString().trim();
            DataUtil.privateData.email = etMail.getText().toString().trim();
            DataUtil.privateData.note = etNote.getText().toString().trim();
            DataUtil.privateData.sex = sex;
            ContentValues contentValues = Database.getInstance().createPrivate(DataUtil.privateData);
            DataUtil.privateData.idPrivate = (int) Database.getInstance().insert(contentValues, Database.getInstance().DATABASE_TABLE_PRIVATE);


            //Create and insert Phone
            DataUtil.phoneData = new Phone();
            DataUtil.phoneData.isCompany = 0;
            DataUtil.phoneData.idPrivate = DataUtil.privateData.idPrivate;
            DataUtil.phoneData.number = etPhone.getText().toString().trim();
            DataUtil.phoneData.idCompany = 0;
            DataUtil.phoneData.note = DataUtil.PHONE_MOBILE_INT;
            contentValues = Database.getInstance().createPhone(DataUtil.phoneData);
            Database.getInstance().insert(contentValues, Database.getInstance().DATABASE_TABLE_PHONE);
            Database.getInstance().close();
            DataUtil.needAddNew = true;
            onBackPressed();
        } else {
            Boast.makeText(AddPrivateActivity.this, "Data wrong !!").show();
        }
    }

    private void addPhoneButton() {
        switch (addCount) {
            case 0:
                lnMorePhone2 = (LinearLayout) findViewById(R.id.lnMorePhone2);
                etPhone2 = (EditText) findViewById(R.id.etPhone2);
                lnMorePhone2.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnMorePhone2.setVisibility(View.VISIBLE);
                addCount++;
                break;
            case 1:
                lnMorePhone3 = (LinearLayout) findViewById(R.id.lnMorePhone3);
                etPhone3 = (EditText) findViewById(R.id.etPhone3);
                lnMorePhone3.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnMorePhone3.setVisibility(View.VISIBLE);
                addCount++;
                break;
            case 2:
                lnMorePhone4 = (LinearLayout) findViewById(R.id.lnMorePhone4);
                etPhone4 = (EditText) findViewById(R.id.etPhone4);
                lnMorePhone4.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnMorePhone4.setVisibility(View.VISIBLE);
                addCount++;
                break;
            case 3:
                lnMorePhone5 = (LinearLayout) findViewById(R.id.lnMorePhone5);
                etPhone5 = (EditText) findViewById(R.id.etPhone5);
                lnMorePhone5.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnMorePhone5.setVisibility(View.VISIBLE);
                addCount++;
                break;
        }
    }


}
