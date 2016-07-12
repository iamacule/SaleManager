package com.hoangphuong2.salemanager.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

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
import com.hoangphuong2.salemanager.ui.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrAn on 16-May-16.
 */
public class AddPrivateActivity extends BaseActivity {
    private final int MALE = 1;
    private final int FEMALE = 0;
    private int sex = MALE;
    private ImageView imgAdd;
    private int addCount = 0;
    private LinearLayout ipName;
    private LinearLayout ipAddress;
    private LinearLayout ipMail;
    private LinearLayout ipNote;
    private LinearLayout ipPhone;
    private LinearLayout ipPhone2;
    private LinearLayout ipPhone3;
    private LinearLayout ipPhone4;
    private LinearLayout ipPhone5;
    private LinearLayout lnParentPhone2;
    private LinearLayout lnParentPhone3;
    private LinearLayout lnParentPhone4;
    private LinearLayout lnParentPhone5;
    private EditText etName;
    private EditText etAddress;
    private EditText etMail;
    private EditText etNote;
    private EditText etPhone;
    private EditText etPhone2;
    private EditText etPhone3;
    private EditText etPhone4;
    private EditText etPhone5;
    private TextInputLayout txtName;
    private TextInputLayout txtMail;
    private TextInputLayout txtNote;
    private TextInputLayout txtAddress;
    private TextInputLayout txtHint;
    private TextInputLayout txtHint2;
    private TextInputLayout txtHint3;
    private TextInputLayout txtHint4;
    private TextInputLayout txtHint5;
    private Spinner spPhone2;
    private Spinner spPhone3;
    private Spinner spPhone4;
    private Spinner spPhone5;
    private ImageView imgLogo;
    private Bitmap bpAdd;
    private Button btnSave;
    private Button btnImport;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private ImportContacts importContacts;
    private ArrayAdapter<String> dataSpinner;
    private List<Phone> listPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_private;
    }

    private void initLayout() {
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnImport = (Button) findViewById(R.id.btnImport);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        listPhone = new ArrayList<>();
        // Creating adapter for spinner
        dataSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DataUtil.listPhoneNote);

        // Drop down layout style - list view with radio button
        dataSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ipName = (LinearLayout) findViewById(R.id.inputName);
        etName = (EditText) ipName.findViewById(R.id.et);
        txtName = (TextInputLayout) ipName.findViewById(R.id.hint);
        txtName.setHint(getString(R.string.add_name_hint));

        ipAddress = (LinearLayout) findViewById(R.id.inputAddress);
        etAddress = (EditText) ipAddress.findViewById(R.id.et);
        txtAddress = (TextInputLayout) ipAddress.findViewById(R.id.hint);
        txtAddress.setHint(getString(R.string.add_address_hint));

        ipMail = (LinearLayout) findViewById(R.id.inputEmail);
        etMail = (EditText) ipMail.findViewById(R.id.et);
        etMail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        txtMail = (TextInputLayout) ipMail.findViewById(R.id.hint);
        txtMail.setHint(getString(R.string.add_mail_hint));

        ipPhone = (LinearLayout) findViewById(R.id.inputPhone);
        etPhone = (EditText) ipPhone.findViewById(R.id.et);
        etPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        txtHint = (TextInputLayout) ipPhone.findViewById(R.id.hint);
        txtHint.setHint(getString(R.string.add_phone_hint));

        ipPhone2 = (LinearLayout) findViewById(R.id.inputPhone2);
        etPhone2 = (EditText) ipPhone2.findViewById(R.id.et);
        etPhone2.setInputType(InputType.TYPE_CLASS_PHONE);
        txtHint2 = (TextInputLayout) ipPhone2.findViewById(R.id.hint);
        txtHint2.setHint(getString(R.string.add_phone_hint));

        ipPhone3 = (LinearLayout) findViewById(R.id.inputPhone3);
        etPhone3 = (EditText) ipPhone3.findViewById(R.id.et);
        etPhone3.setInputType(InputType.TYPE_CLASS_PHONE);
        txtHint3 = (TextInputLayout) ipPhone3.findViewById(R.id.hint);
        txtHint3.setHint(getString(R.string.add_phone_hint));

        ipPhone4 = (LinearLayout) findViewById(R.id.inputPhone4);
        etPhone4 = (EditText) ipPhone4.findViewById(R.id.et);
        etPhone4.setInputType(InputType.TYPE_CLASS_PHONE);
        txtHint4 = (TextInputLayout) ipPhone4.findViewById(R.id.hint);
        txtHint4.setHint(getString(R.string.add_phone_hint));

        ipPhone5 = (LinearLayout) findViewById(R.id.inputPhone5);
        etPhone5 = (EditText) ipPhone5.findViewById(R.id.et);
        etPhone5.setInputType(InputType.TYPE_CLASS_PHONE);
        txtHint5 = (TextInputLayout) ipPhone5.findViewById(R.id.hint);
        txtHint5.setHint(getString(R.string.add_phone_hint));

        ipNote = (LinearLayout) findViewById(R.id.inputNote);
        etNote = (EditText) ipNote.findViewById(R.id.et);
        txtNote = (TextInputLayout) ipNote.findViewById(R.id.hint);
        txtNote.setHint(getString(R.string.add_note_hint));

        spPhone2 = (Spinner) findViewById(R.id.spPhone2);
        spPhone3 = (Spinner) findViewById(R.id.spPhone3);
        spPhone4 = (Spinner) findViewById(R.id.spPhone4);
        spPhone5 = (Spinner) findViewById(R.id.spPhone5);

        lnParentPhone2 = (LinearLayout) findViewById(R.id.lnParentPhone2);
        lnParentPhone3 = (LinearLayout) findViewById(R.id.lnParentPhone3);
        lnParentPhone4 = (LinearLayout) findViewById(R.id.lnParentPhone4);
        lnParentPhone5 = (LinearLayout) findViewById(R.id.lnParentPhone5);

        // attaching data adapter to spinner
        spPhone2.setAdapter(dataSpinner);
        spPhone2.setSelection(1);

        // attaching data adapter to spinner
        spPhone3.setAdapter(dataSpinner);
        spPhone3.setSelection(1);

        // attaching data adapter to spinner
        spPhone4.setAdapter(dataSpinner);
        spPhone4.setSelection(1);

        // attaching data adapter to spinner
        spPhone5.setAdapter(dataSpinner);
        spPhone5.setSelection(1);

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
        listPhone.clear();
        if (etName.getText().toString().trim().equals("")) {
            txtName.setError(null);
            txtName.setError(getString(R.string.name_emplty));
            etName.requestFocus();
        } else if (etName.getText().toString().trim().length() < 3) {
            txtName.setError(getString(R.string.min_lenge));
            etName.requestFocus();

        } else if (etName.getText().toString().trim().length() > 20) {
            txtName.setError(getString(R.string.max_lenge));
            etName.requestFocus();
        } else if (etAddress.getText().toString().trim().equals("")) {
            txtAddress.setError(null);
            txtAddress.setError(getString(R.string.add_address_hint));
            etAddress.requestFocus();
        } else if (etMail.getText().toString().trim().equals("")) {
            txtMail.setError(null);
            txtMail.setError(getString(R.string.add_mail_hint));
            etMail.requestFocus();
        } else if (!etMail.getText().toString().trim().matches(String.valueOf(Patterns.EMAIL_ADDRESS))) {
            txtMail.setError(null);
            txtMail.setError(getString(R.string.add_mail_error));
            etMail.requestFocus();
        } else if (etPhone.getText().toString().trim().equals("")) {
            txtHint.setError(null);
            txtHint.setError(getString(R.string.add_phone_hint));
            etPhone.requestFocus();
        } else if (!etPhone.getText().toString().trim().matches(String.valueOf(Patterns.PHONE))) {
            txtHint.setError(null);
            txtHint.setError(getString(R.string.add_phone_error));
            etPhone.requestFocus();
        } else if (lnParentPhone2.getVisibility() == View.VISIBLE &&
                !etPhone2.getText().toString().trim().matches(String.valueOf(Patterns.PHONE))) {
            txtHint2.setError(null);
            txtHint2.setError(getString(R.string.add_phone_error));
            etPhone2.requestFocus();
        } else if (lnParentPhone3.getVisibility() == View.VISIBLE &&
                !etPhone3.getText().toString().trim().matches(String.valueOf(Patterns.PHONE))) {
            txtHint3.setError(null);
            txtHint3.setError(getString(R.string.add_phone_error));
            etPhone3.requestFocus();
        } else if (lnParentPhone4.getVisibility() == View.VISIBLE &&
                !etPhone4.getText().toString().trim().matches(String.valueOf(Patterns.PHONE))) {
            txtHint4.setError(null);
            txtHint4.setError(getString(R.string.add_phone_error));
            etPhone4.requestFocus();
        } else if (lnParentPhone5.getVisibility() == View.VISIBLE &&
                !etPhone5.getText().toString().trim().matches(String.valueOf(Patterns.PHONE))) {
            txtHint5.setError(null);
            txtHint5.setError(getString(R.string.add_phone_error));
            etPhone5.requestFocus();
        } else {
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
            DataUtil.phoneData.idPerson = DataUtil.personData.idPerson;
            DataUtil.phoneData.isCompany = 0;
            DataUtil.phoneData.idCompany = 0;
            DataUtil.phoneData.note = 0;
            DataUtil.phoneData.number = etPhone.getText().toString();
            listPhone.add(DataUtil.phoneData);

            if (lnParentPhone2.getVisibility() == View.VISIBLE) {
                DataUtil.phoneData.note = spPhone2.getSelectedItemPosition();
                DataUtil.phoneData.number = etPhone2.getText().toString();
                Log.d(Tag.AddPrivateActivity,"Phone number 2 : "+DataUtil.phoneData.number);
                listPhone.add(DataUtil.phoneData);
            }
            if (lnParentPhone3.getVisibility() == View.VISIBLE) {
                DataUtil.phoneData.note = spPhone3.getSelectedItemPosition();
                DataUtil.phoneData.number = etPhone3.getText().toString();
                Log.d(Tag.AddPrivateActivity,"Phone number 3 : "+DataUtil.phoneData.number);
                listPhone.add(DataUtil.phoneData);
            }
            if (lnParentPhone4.getVisibility() == View.VISIBLE) {
                DataUtil.phoneData.note = spPhone4.getSelectedItemPosition();
                DataUtil.phoneData.number = etPhone4.getText().toString();
                Log.d(Tag.AddPrivateActivity,"Phone number 4 : "+DataUtil.phoneData.number);
                listPhone.add(DataUtil.phoneData);
            }
            if (lnParentPhone5.getVisibility() == View.VISIBLE) {
                DataUtil.phoneData.note = spPhone5.getSelectedItemPosition();
                DataUtil.phoneData.number = etPhone5.getText().toString();
                Log.d(Tag.AddPrivateActivity,"Phone number 5 : "+DataUtil.phoneData.number);
                listPhone.add(DataUtil.phoneData);
            }
            Log.e(Tag.AddPrivateActivity, "Size list phone : " + listPhone.size());
            for (Phone phone : listPhone) {
                Log.e(Tag.AddPrivateActivity, phone.number + " added");
                contentValues = Database.getInstance().createPhone(phone);
                Database.getInstance().insert(contentValues, Database.getInstance().DATABASE_TABLE_PHONE);
            }
            DataUtil.personData.listPhone = listPhone;
            Database.getInstance().close();
            DataUtil.needAddNew = true;
            onBackPressed();
        }
    }

    private void addPhoneButton() {
        Log.d(Tag.AddPrivateActivity, "Add phone clicked");
        switch (addCount) {
            case 0:
                lnParentPhone2.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnParentPhone2.setVisibility(View.VISIBLE);
                addCount++;
                break;
            case 1:
                lnParentPhone3.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnParentPhone3.setVisibility(View.VISIBLE);
                addCount++;
                break;
            case 2:
                lnParentPhone4.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnParentPhone4.setVisibility(View.VISIBLE);
                addCount++;
                break;
            case 3:
                lnParentPhone5.startAnimation(AnimationUtil.slideInTop(AddPrivateActivity.this));
                lnParentPhone5.setVisibility(View.VISIBLE);
                addCount++;
                break;
        }
    }

    @Override
    public void identifyLayout() {
        initLayout();
    }

    @Override
    public void identityValue() {
        prepareValue();
    }

    @Override
    public void identityAction() {
        setOnClick();
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
            List<Person> listPersonCurrent;
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
