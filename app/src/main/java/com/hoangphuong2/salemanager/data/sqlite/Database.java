package com.hoangphuong2.salemanager.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hoangphuong2.salemanager.model.Address;
import com.hoangphuong2.salemanager.model.Bill;
import com.hoangphuong2.salemanager.model.Company;
import com.hoangphuong2.salemanager.model.Item;
import com.hoangphuong2.salemanager.model.ItemType;
import com.hoangphuong2.salemanager.model.Phone;
import com.hoangphuong2.salemanager.model.Private;
import com.hoangphuong2.salemanager.model.Sale;


/**
 * Create by An.Pham 22/04/2016
 */
public class Database {
    private Context mContext;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDB;
    private ContentValues contentValues;

    private final String DATABASE_NAME = "SALE_MANAGER";
    public final String DATABASE_TABLE_PRIVATE = "DATABASE_TABLE_PRIVATE";
    private final String DATABASE_CREATE_PRIVATE = "CREATE TABLE DATABASE_TABLE_PRIVATE (" +
            "PRIVATE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "PRIVATE_NAME TEXT," +
            "PRIVATE_EMAIL TEXT," +
            "PRIVATE_NOTE TEXT," +
            "PRIVATE_SEX INT," +
            "COMPANY_ID INT);";

    public final String DATABASE_TABLE_PHONE = "DATABASE_TABLE_PHONE";
    private final String DATABASE_CREATE_PHONE = "CREATE TABLE DATABASE_TABLE_PHONE (" +
            "PHONE_NUMBER INTEGER PRIMARY KEY," +
            "PHONE_NOTE TEXT," +
            "PRIVATE_ID INT," +
            "COMPANY_ID INT," +
            "IS_COMPANY INT);";

    public final String DATABASE_TABLE_ADDRESS = "DATABASE_TABLE_ADDRESS";
    private final String DATABASE_CREATE_ADDRESS = "CREATE TABLE DATABASE_TABLE_ADDRESS (" +
            "ADDRESS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ADDRESS TEXT," +
            "PRIVATE_ID INT," +
            "COMPANY_ID INT," +
            "IS_BILL_ADDRESS INT," +
            "IS_COMPANY INT);";

    public final String DATABASE_TABLE_COMPANY = "DATABASE_TABLE_COMPANY";
    private final String DATABASE_CREATE_COMPANY = "CREATE TABLE DATABASE_TABLE_COMPANY (" +
            "COMPANY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "COMPANY_NAME TEXT," +
            "COMPANY_NOTE TEXT," +
            "COMPANY_TAX TEXT);";

    public final String DATABASE_TABLE_ITEM_TYPE = "DATABASE_TABLE_ITEM_TYPE";
    private final String DATABASE_CREATE_ITEM_TYPE = "CREATE TABLE DATABASE_TABLE_ITEM_TYPE (" +
            "ITEM_TYPE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ITEM_TYPE_NAME TEXT," +
            "ITEM_TYPE_NOTE TEXT);";

    public final String DATABASE_TABLE_ITEM = "DATABASE_TABLE_ITEM";
    private final String DATABASE_CREATE_ITEM = "CREATE TABLE DATABASE_TABLE_ITEM (" +
            "ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ITEM_NAME TEXT," +
            "ITEM_NAME_SPECIFICATION TEXT," +
            "ITEM_TYPE_ID INT," +
            "ITEM_NOTE TEXT);";

    public final String DATABASE_TABLE_SALE = "DATABASE_TABLE_SALE";
    private final String DATABASE_CREATE_SALE = "CREATE TABLE DATABASE_TABLE_SALE (" +
            "ITEM_ID INTEGER," +
            "PRIVATE_ID INT," +
            "COMPANY_ID INT," +
            "SALE_PRICE TEXT," +
            "SALE_NOTE TEXT);";

    public final String DATABASE_TABLE_BILL = "DATABASE_TABLE_BILL";
    private final String DATABASE_CREATE_BILL = "CREATE TABLE DATABASE_TABLE_BILL (" +
            "BILL_NUMBER INTEGER PRIMARY KEY," +
            "PRIVATE_ID INT," +
            "COMPANY_ID INT," +
            "BILL_DATE TEXT," +
            "BILL_TOTAL INT," +
            "IS_PAYED INT," +
            "IS_PAY_TYPE INT," +
            "BILL_NOTE TEXT);";


    //Define Column name
    public final String PRIVATE_ID = "PRIVATE_ID";
    public final String PRIVATE_NAME = "PRIVATE_NAME";
    public final String PRIVATE_EMAIL = "PRIVATE_EMAIL";
    public final String PRIVATE_NOTE = "PRIVATE_NOTE";
    public final String PRIVATE_SEX = "PRIVATE_SEX";
    public final String COMPANY_ID = "COMPANY_ID";

    public final String PHONE_NUMBER = "PHONE_NUMBER";
    public final String PHONE_NOTE = "PHONE_NOTE";
    public final String IS_COMPANY = "IS_COMPANY";

    public String ADDRESS_ID = "ADDRESS_ID";
    public String ADDRESS = "ADDRESS";
    public String IS_BILL_ADDRESS = "IS_BILL_ADDRESS";

    public String COMPANY_NAME = "COMPANY_NAME";
    public String COMPANY_TAX = "COMPANY_TAX";
    public String COMPANY_NOTE = "COMPANY_NOTE";

    public String ITEM_TYPE_ID = "ITEM_TYPE_ID";
    public String ITEM_TYPE_NAME = "ITEM_TYPE_NAME";
    public String ITEM_TYPE_NOTE = "ITEM_TYPE_NOTE";

    public String ITEM_ID = "ITEM_ID";
    public String ITEM_NAME = "ITEM_NAME";
    public String ITEM_NAME_SPECIFICATION = "ITEM_NAME_SPECIFICATION";
    public String ITEM_NOTE = "ITEM_NOTE";

    public String SALE_PRICE = "SALE_PRICE";
    public String SALE_NOTE = "SALE_NOTE";

    public String BILL_NUMBER = "BILL_NUMBER";
    public String BILL_DATE = "BILL_DATE";
    public String BILL_TOTAL = "BILL_TOTAL";
    public String IS_PAYED = "IS_PAYED";
    public String IS_PAY_TYPE = "IS_PAY_TYPE";
    public String BILL_NOTE = "BILL_NOTE";


    public class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_ADDRESS);
            db.execSQL(DATABASE_CREATE_BILL);
            db.execSQL(DATABASE_CREATE_COMPANY);
            db.execSQL(DATABASE_CREATE_ITEM);
            db.execSQL(DATABASE_CREATE_ITEM_TYPE);
            db.execSQL(DATABASE_CREATE_PHONE);
            db.execSQL(DATABASE_CREATE_PRIVATE);
            db.execSQL(DATABASE_CREATE_SALE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("GO TO UPGRADE DATABASE", "TRUE");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ADDRESS);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_BILL);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_COMPANY);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ITEM);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ITEM_TYPE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PHONE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_PRIVATE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SALE);
            db.execSQL(DATABASE_CREATE_ADDRESS);
            db.execSQL(DATABASE_CREATE_BILL);
            db.execSQL(DATABASE_CREATE_COMPANY);
            db.execSQL(DATABASE_CREATE_ITEM);
            db.execSQL(DATABASE_CREATE_ITEM_TYPE);
            db.execSQL(DATABASE_CREATE_PHONE);
            db.execSQL(DATABASE_CREATE_PRIVATE);
            db.execSQL(DATABASE_CREATE_SALE);
            db.setVersion(newVersion);
        }
    }

    public Database(Context context) {
        this.mContext = context;
    }

    public Database open() {
        try {
            mDBHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, 1);
            mDB = mDBHelper.getWritableDatabase();
        } catch (Exception e) {
            mDB = mDBHelper.getReadableDatabase();
            close();
            mDBHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, 1);
            mDB = mDBHelper.getWritableDatabase();
        }

        return this;
    }

    public void close() {
        if (mDBHelper != null) {
            mDBHelper.close();
        }
    }

    public ContentValues createPrivate(Private data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(PRIVATE_NAME, data.name);
        contentValues.put(PRIVATE_EMAIL, data.email);
        contentValues.put(PRIVATE_NOTE, data.note);
        contentValues.put(PRIVATE_SEX, data.sex);
        contentValues.put(COMPANY_ID, data.idCompany);
        return contentValues;
    }

    public ContentValues createPhone(Phone data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(PHONE_NUMBER, data.number);
        contentValues.put(PHONE_NOTE, data.note);
        contentValues.put(PRIVATE_ID, data.idPrivate);
        contentValues.put(COMPANY_ID, data.isCompany);
        contentValues.put(IS_COMPANY, data.isCompany);
        return contentValues;
    }

    public ContentValues createAddress(Address data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(ADDRESS, data.address);
        contentValues.put(PRIVATE_ID, data.idPrivate);
        contentValues.put(COMPANY_ID, data.idCompany);
        contentValues.put(IS_BILL_ADDRESS, data.idBillAddress);
        contentValues.put(IS_COMPANY, data.isCompany);
        return contentValues;
    }

    public ContentValues createCompany(Company data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(COMPANY_NAME, data.name);
        contentValues.put(COMPANY_NOTE, data.note);
        contentValues.put(COMPANY_TAX, data.tax);
        return contentValues;
    }

    public ContentValues createItemType(ItemType data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(ITEM_TYPE_NAME, data.name);
        contentValues.put(ITEM_TYPE_NOTE, data.note);
        return contentValues;
    }

    public ContentValues createItem(Item data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(ITEM_NAME, data.name);
        contentValues.put(ITEM_NAME_SPECIFICATION, data.specifications);
        contentValues.put(ITEM_TYPE_ID, data.idItemType);
        contentValues.put(ITEM_NOTE, data.note);
        return contentValues;
    }


    public ContentValues createSale(Sale data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(ITEM_ID, data.idItem);
        contentValues.put(PRIVATE_ID, data.idPrivate);
        contentValues.put(COMPANY_ID, data.idCompany);
        contentValues.put(SALE_PRICE, data.price);
        contentValues.put(SALE_NOTE, data.note);
        return contentValues;
    }

    public ContentValues createBill(Bill data) {
        contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(BILL_NUMBER, data.number);
        contentValues.put(PRIVATE_ID, data.idPrivate);
        contentValues.put(COMPANY_ID, data.idCompany);
        contentValues.put(BILL_DATE, data.date);
        contentValues.put(BILL_TOTAL, data.total);
        contentValues.put(IS_PAYED, data.payed);
        contentValues.put(IS_PAY_TYPE, data.payType);
        contentValues.put(BILL_NOTE, data.note);
        return contentValues;
    }

    public long insert(ContentValues contentValues, String table) {
        boolean ss = false;
        while (!ss) {
            try {
                mDB.beginTransaction();
                ss = true;
            } catch (Exception e) {
                mDB = mDBHelper.getReadableDatabase();
                return -1;
            }
        }
        long idInsert = 0;
        try {
            idInsert = mDB.insertWithOnConflict(table, null,
                    contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            mDB = mDBHelper.getReadableDatabase();
        } finally {
            mDB.endTransaction();
        }
        return idInsert;
    }

    public Private getPrivate(int id) {
        Cursor mCursor = null;
        Private data = new Private();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_PRIVATE
                    , new String[]{PRIVATE_ID, PRIVATE_NAME, PRIVATE_EMAIL, PRIVATE_SEX, PRIVATE_NOTE}
                    , PRIVATE_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.idPrivate = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.email = mCursor.getString(2);
                    data.sex = mCursor.getInt(3);
                    data.note = mCursor.getString(4);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }
}