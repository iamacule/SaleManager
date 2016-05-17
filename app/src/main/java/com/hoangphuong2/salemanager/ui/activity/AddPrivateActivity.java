package com.hoangphuong2.salemanager.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.hoangphuong2.salemanager.R;
import com.hoangphuong2.salemanager.api.GetAccessToken;
import com.hoangphuong2.salemanager.api.GoogleConstants;
import com.hoangphuong2.salemanager.data.sqlite.Database;
import com.hoangphuong2.salemanager.dialog.DialogAsk;
import com.hoangphuong2.salemanager.dialog.PDialog;
import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Private;
import com.hoangphuong2.salemanager.ui.control.OnSingleClickListener;
import com.hoangphuong2.salemanager.ui.toast.Boast;
import com.hoangphuong2.salemanager.ui.util.AnimationUtil;
import com.hoangphuong2.salemanager.util.DataUtil;
import com.hoangphuong2.salemanager.util.PermissionUtil;
import com.hoangphuong2.salemanager.util.ResizeBitmap;
import com.hoangphuong2.salemanager.util.Tag;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
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
    private Dialog dialogRequestContactFromGoogle;

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
//            startImportContactsAsyncTask();
            launchAuthDialog();
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

    private void launchAuthDialog() {
        final Context context = this;
        dialogRequestContactFromGoogle = new Dialog(context);
        dialogRequestContactFromGoogle.setTitle("WebView");
        dialogRequestContactFromGoogle.setCancelable(true);
        dialogRequestContactFromGoogle.setContentView(R.layout.auth_dialog);

        dialogRequestContactFromGoogle.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });

        WebView web = (WebView) dialogRequestContactFromGoogle.findViewById(R.id.webv);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(GoogleConstants.OAUTH_URL+"?redirect_uri="+GoogleConstants.REDIRECT_URI
                +"&response_type=code&client_id="+GoogleConstants.CLIENT_ID+"&scope="+GoogleConstants.OAUTH_SCOPE);
        web.setWebViewClient(new WebViewClient() {
            boolean authComplete = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("?code=") && authComplete != true) {
                    Uri uri = Uri.parse(url);
                    String authCode = uri.getQueryParameter("code");
                    authComplete = true;
                    dialogRequestContactFromGoogle.dismiss();
                    new GoogleAuthToken(context).execute(authCode);
                } else if (url.contains("error=access_denied")) {
                    Log.i("", "ACCESS_DENIED_HERE");
                    authComplete = true;
                    dialogRequestContactFromGoogle.dismiss();
                }
            }
        });
        dialogRequestContactFromGoogle.show();
    }

    private class GoogleAuthToken extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        private Context context;

        public GoogleAuthToken(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Contacting Google ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            String authCode = args[0];
            GetAccessToken jParser = new GetAccessToken();
            JSONObject json = jParser.getToken(GoogleConstants.TOKEN_URL,
                    authCode, GoogleConstants.CLIENT_ID,
                    GoogleConstants.CLIENT_SECRET,
                    GoogleConstants.REDIRECT_URI, GoogleConstants.GRANT_TYPE);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            if (json != null) {
                try {
                    String tok = json.getString("access_token");
                    String expire = json.getString("expires_in");
                    String refresh = json.getString("refresh_token");
                    new GetGoogleContacts(context).execute(tok);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class GetGoogleContacts extends
            AsyncTask<String, String, List<ContactEntry>> {

        private ProgressDialog pDialog;
        private Context context;

        public GetGoogleContacts(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Authenticated. Getting Google Contacts ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
            pDialog.show();
        }

        @Override
        protected List<ContactEntry> doInBackground(String... args) {
            String accessToken = args[0];
            ContactsService contactsService = new ContactsService(
                    GoogleConstants.APP);
            contactsService.setHeader("Authorization", "Bearer " + accessToken);
            contactsService.setHeader("GData-Version", "3.0");
            List<ContactEntry> contactEntries = null;
            try {
                URL feedUrl = new URL(GoogleConstants.CONTACTS_URL);
                Query myQuery = new Query(feedUrl);
                myQuery.setMaxResults(GoogleConstants.MAX_NB_CONTACTS);
                ContactFeed resultFeed = contactsService.getFeed(myQuery,
                        ContactFeed.class);
                contactEntries = resultFeed.getEntries();
            } catch (Exception e) {
                pDialog.dismiss();
                Toast.makeText(context, "Failed to get Contacts",
                        Toast.LENGTH_SHORT).show();
            }
            return contactEntries;
        }

        @Override
        protected void onPostExecute(List<ContactEntry> googleContacts) {
            if (null != googleContacts && googleContacts.size() > 0) {
                StringBuffer output = new StringBuffer();

                for (ContactEntry contactEntry : googleContacts) {
                    String name = "";
                    String email = "";
                    List<PhoneNumber> phoneNumber = new ArrayList<>();

                    if (contactEntry.hasName()) {
                        Name tmpName = contactEntry.getName();
                        if (tmpName.hasFullName()) {
                            name = tmpName.getFullName().getValue();
                        } else {
                            if (tmpName.hasGivenName()) {
                                name = tmpName.getGivenName().getValue();
                                if (tmpName.getGivenName().hasYomi()) {
                                    name += " ("
                                            + tmpName.getGivenName().getYomi()
                                            + ")";
                                }
                                if (tmpName.hasFamilyName()) {
                                    name += tmpName.getFamilyName().getValue();
                                    if (tmpName.getFamilyName().hasYomi()) {
                                        name += " ("
                                                + tmpName.getFamilyName()
                                                .getYomi() + ")";
                                    }
                                }
                            }
                        }
                    }

                    if (contactEntry.hasPhoneNumbers()) {
                        phoneNumber = contactEntry.getPhoneNumbers();
                    }
                    List<Email> emails = contactEntry.getEmailAddresses();
                    if (null != emails && emails.size() > 0) {
                        Email tempEmail = (Email) emails.get(0);
                        email = tempEmail.getAddress();
                    }
                    output.append(name + "\n");
                    output.append(email + "\n");
                    for (PhoneNumber data : phoneNumber) {
                        output.append(data.getPhoneNumber() + ",");
                    }
                    output.append("\n");
                }
                Log.e(Tag.AddPrivateActivity, output.toString());

            } else {
                Log.e(Tag.AddPrivateActivity, "No Contact Found.");
                Toast.makeText(context, "No Contact Found.", Toast.LENGTH_SHORT)
                        .show();
            }
            pDialog.dismiss();
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

    private class ImportContacts extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PDialog.show(AddPrivateActivity.this, getString(R.string.loading));
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            PDialog.dismiss();
        }
    }
}
