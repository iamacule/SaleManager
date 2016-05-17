package com.hoangphuong2.salemanager.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.data.sqlite.Database;
import com.hoangphuong2.salemanager.dialog.DialogAsk;
import com.hoangphuong2.salemanager.dialog.DialogInfo;
import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Person;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.ui.util.AnimationUtil;
import com.hoangphuong2.salemanager.util.DataUtil;
import com.hoangphuong2.salemanager.util.PermissionUtil;
import com.hoangphuong2.salemanager.util.ResizeBitmap;

import java.util.ArrayList;
import java.util.List;

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
    private Button btnImport;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private ImportContacts importContacts;

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
        btnImport = (Button) findViewById(R.id.btnImport);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);

        prepareValue();
        setOnClick();
    }

    private void startImportContactsAsyncTask() {
        if (importContacts != null && !importContacts.isCancelled()) {
            importContacts.cancel(true);
        }
        importContacts = new ImportContacts();
        importContacts.execute(new Void[]{});
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
                    case R.id.btnImport:
                        prepareImport();
                        break;
                }
            }
        };
        imgAdd.setOnClickListener(click);
        btnSave.setOnClickListener(click);
        radioMale.setOnClickListener(click);
        radioFemale.setOnClickListener(click);
        btnImport.setOnClickListener(click);
    }

    private void prepareImport() {
        if (PermissionUtil.permissionReadContacts) {
            startImportContactsAsyncTask();
        } else {
            showDialogAskPermission();
        }
    }

    private void showDialogAskPermission() {
        DialogAsk.createDialog(this, getString(R.string.ask_read_contact));
        DialogAsk.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAsk.dialog.dismiss();
                if (!PermissionUtil.permissionReadContacts) {
                    PermissionUtil.request(AddPrivateActivity.this, Manifest.permission.READ_CONTACTS, PermissionUtil.READ_CONTACTS);
                }
            }
        });
        DialogAsk.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAsk.dialog.dismiss();
            }
        });
        DialogAsk.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtil.READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PermissionUtil.permissionReadContacts = true;
                } else {
                    PermissionUtil.permissionReadContacts = false;
                }
                return;
            }
        }
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
            //Create and insert Person
            DataUtil.personData = new Person();
            DataUtil.personData.name = etName.getText().toString().trim();
            DataUtil.personData.address = etAddress.getText().toString().trim();
            DataUtil.personData.email = etMail.getText().toString().trim();
            DataUtil.personData.note = etNote.getText().toString().trim();
            DataUtil.personData.sex = sex;
            ContentValues contentValues = Database.getInstance().createPerson(DataUtil.personData);
            DataUtil.personData.idPerson = (int) Database.getInstance().insert(contentValues, Database.getInstance().DATABASE_TABLE_PERSON);


            //Create and insert Phone
            DataUtil.phoneData = new Phone();
            DataUtil.phoneData.isCompany = 0;
            DataUtil.phoneData.idPerson = DataUtil.personData.idPerson;
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

    private class ImportContacts extends AsyncTask<Void, Integer, Boolean> {

        private int total;
        private int count;

        public ImportContacts() {
            count = 0;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DialogInfo.createDialog(AddPrivateActivity.this, AddPrivateActivity.this.getString(R.string.get_contact_phone));
            DialogInfo.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Database.getInstance().open();
            List<Person> listPersonCurrent = new ArrayList<>();
            List<Phone> listPhoneCurrent = new ArrayList<>();
            listPersonCurrent = Database.getInstance().getAllPerson();
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            total = cur.getCount();
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    count++;
                    publishProgress(count);
                    Person person = new Person();
                    List<Phone> listPhone = new ArrayList<>();
                    List<String> listEmail = new ArrayList<>();
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    if (name != null && !name.equals("")) {
                        person.name = name.trim();
                        if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                            // get the phone number
                            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                    new String[]{id}, null);
                            while (pCur.moveToNext()) {
                                String phoneNumber = pCur.getString(
                                        pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                if (phoneNumber != null && !phoneNumber.equals("")) {
                                    Phone phone = new Phone();
                                    phone.number = phoneNumber;
                                    phone.isCompany = 0;
                                    phone.idCompany = 0;
                                    phone.note = DataUtil.PHONE_MOBILE_INT;
                                    listPhone.add(phone);
                                }
                            }
                            pCur.close();
                            person.listPhone = listPhone;

                            // get email and type
                            Cursor emailCur = cr.query(
                                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                    new String[]{id}, null);
                            while (emailCur.moveToNext()) {
                                // This would allow you get several email addresses
                                // if the email addresses were stored in an array
                                String email = emailCur.getString(
                                        emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                                listEmail.add(email);
                            }
                            emailCur.close();
                            if (listEmail.size() > 0) {
                                person.email = listEmail.get(0);
                            }
                        }
                        person.note = AddPrivateActivity.this.getString(R.string.import_from_contacts);

                        boolean isDataPersonExist = false;
                        ContentValues contentValues;
                        for (Person p : listPersonCurrent) {
                            if (p.name.equals(person.name)) {
                                isDataPersonExist = true;
                            }
                        }
                        if (!isDataPersonExist) {
                            //Insert to Database
                            contentValues = Database.getInstance().createPerson(person);
                            int idPerson = (int) Database.getInstance().insert(contentValues, Database.getInstance().DATABASE_TABLE_PERSON);
                            //Create and insert Phone
                            for (Phone phone : listPhone) {
                                phone.idPerson = idPerson;
                                contentValues = Database.getInstance().createPhone(phone);
                                Database.getInstance().insert(contentValues, Database.getInstance().DATABASE_TABLE_PHONE);
                            }
                        }
                    }
                }
            }
            Database.getInstance().close();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            DialogInfo.txtProgress.setText(values[0] + "/" + total);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            DataUtil.needRefresh = true;
            DialogInfo.dismiss();
            Boast.makeText(AddPrivateActivity.this, AddPrivateActivity.this.getString(R.string.import_contact_success)).show();
        }
    }
}
