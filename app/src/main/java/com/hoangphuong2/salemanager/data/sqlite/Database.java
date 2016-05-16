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
import com.hoangphuong2.salemanager.util.Tag;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by An.Pham 22/04/2016
 */
public class Database {
    private static Context mContext;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDB;
    private ContentValues contentValues;
    private static Database instance;

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
            "PHONE_NOTE INT," +
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
            "ADDRESS_NOTE TEXT," +
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
    public String ADDRESS_NOTE = "ADDRESS_NOTE";

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
    private String stringValue;
    private int intValue;


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

    public static void setUp(Context context) {
        mContext = context;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Database() {
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
        contentValues.put(IS_BILL_ADDRESS, data.isBillAddress);
        contentValues.put(IS_COMPANY, data.isCompany);
        contentValues.put(ADDRESS_NOTE, data.note);
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

    public List<Private> getAllPrivate() {
        Cursor mCursor = null;
        List<Private> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_PRIVATE
                    , new String[]{PRIVATE_ID, PRIVATE_NAME, PRIVATE_EMAIL, PRIVATE_SEX, PRIVATE_NOTE}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Private data = new Private();
                    data.idPrivate = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.email = mCursor.getString(2);
                    data.sex = mCursor.getInt(3);
                    data.note = mCursor.getString(4);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public Phone getPhone(int id) {
        Cursor mCursor = null;
        Phone data = new Phone();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_PHONE
                    , new String[]{PHONE_NUMBER, PRIVATE_ID, COMPANY_ID, IS_COMPANY, PHONE_NOTE}
                    , PRIVATE_ID + " = " + id + " OR " + COMPANY_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.number = mCursor.getString(0);
                    data.idPrivate = mCursor.getInt(1);
                    data.isCompany = mCursor.getInt(2);
                    data.isCompany = mCursor.getInt(3);
                    data.note = mCursor.getInt(4);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<Phone> getAllPhoneOfPerson(int id) {
        Cursor mCursor = null;
        List<Phone> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_PHONE
                    , new String[]{PHONE_NUMBER, PRIVATE_ID, COMPANY_ID, IS_COMPANY, PHONE_NOTE}
                    , PRIVATE_ID + " = " + id + " OR " + COMPANY_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Phone data = new Phone();
                    data.number = mCursor.getString(0);
                    data.idPrivate = mCursor.getInt(1);
                    data.isCompany = mCursor.getInt(2);
                    data.isCompany = mCursor.getInt(3);
                    data.note = mCursor.getInt(4);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public Address getAddress(int id) {
        Cursor mCursor = null;
        Address data = new Address();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_ADDRESS
                    , new String[]{ADDRESS_ID, ADDRESS, PRIVATE_ID, COMPANY_ID, IS_COMPANY, IS_BILL_ADDRESS, ADDRESS_NOTE}
                    , ADDRESS_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.idAddress = mCursor.getInt(0);
                    data.address = mCursor.getString(1);
                    data.idPrivate = mCursor.getInt(2);
                    data.isCompany = mCursor.getInt(3);
                    data.isCompany = mCursor.getInt(4);
                    data.isBillAddress = mCursor.getInt(5);
                    data.note = mCursor.getString(6);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<Address> getAllAddress() {
        Cursor mCursor = null;
        List<Address> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_ADDRESS
                    , new String[]{ADDRESS_ID, ADDRESS, PRIVATE_ID, COMPANY_ID, IS_COMPANY, IS_BILL_ADDRESS, ADDRESS_NOTE}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Address data = new Address();
                    data.idAddress = mCursor.getInt(0);
                    data.address = mCursor.getString(1);
                    data.idPrivate = mCursor.getInt(2);
                    data.isCompany = mCursor.getInt(3);
                    data.isCompany = mCursor.getInt(4);
                    data.isBillAddress = mCursor.getInt(5);
                    data.note = mCursor.getString(6);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public Company getCompany(int id) {
        Cursor mCursor = null;
        Company data = new Company();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_COMPANY
                    , new String[]{COMPANY_ID, COMPANY_NAME, COMPANY_TAX, COMPANY_NOTE}
                    , COMPANY_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.idCompany = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.tax = mCursor.getString(2);
                    data.note = mCursor.getString(3);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<Company> getAllCompany() {
        Cursor mCursor = null;
        List<Company> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_COMPANY
                    , new String[]{COMPANY_ID, COMPANY_NAME, COMPANY_TAX, COMPANY_NOTE}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Company data = new Company();
                    data.idCompany = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.tax = mCursor.getString(2);
                    data.note = mCursor.getString(3);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public ItemType getItemType(int id) {
        Cursor mCursor = null;
        ItemType data = new ItemType();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_ITEM_TYPE
                    , new String[]{ITEM_TYPE_ID, ITEM_TYPE_NAME, ITEM_TYPE_NOTE}
                    , ITEM_TYPE_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.idItemType = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.note = mCursor.getString(2);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<ItemType> getAllItemType() {
        Cursor mCursor = null;
        List<ItemType> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_ITEM_TYPE
                    , new String[]{ITEM_TYPE_ID, ITEM_TYPE_NAME, ITEM_TYPE_NOTE}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    ItemType data = new ItemType();
                    data.idItemType = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.note = mCursor.getString(2);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public Item getItem(int id) {
        Cursor mCursor = null;
        Item data = new Item();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_ITEM
                    , new String[]{ITEM_ID, ITEM_NAME, ITEM_NAME_SPECIFICATION, ITEM_NOTE, ITEM_TYPE_ID}
                    , ITEM_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.idItem = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.specifications = mCursor.getString(2);
                    data.note = mCursor.getString(3);
                    data.idItemType = mCursor.getInt(4);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<Item> getAllItem() {
        Cursor mCursor = null;
        List<Item> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_ITEM
                    , new String[]{ITEM_ID, ITEM_NAME, ITEM_NAME_SPECIFICATION, ITEM_NOTE, ITEM_TYPE_ID}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Item data = new Item();
                    data.idItem = mCursor.getInt(0);
                    data.name = mCursor.getString(1);
                    data.specifications = mCursor.getString(2);
                    data.note = mCursor.getString(3);
                    data.idItemType = mCursor.getInt(4);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public Sale getSale(int id) {
        Cursor mCursor = null;
        Sale data = new Sale();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_SALE
                    , new String[]{ITEM_ID, PRIVATE_ID, COMPANY_ID, SALE_PRICE, SALE_NOTE}
                    , PRIVATE_ID + " = " + id + " OR " + COMPANY_ID + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.idItem = mCursor.getInt(0);
                    data.idPrivate = mCursor.getInt(1);
                    data.idCompany = mCursor.getInt(2);
                    data.price = mCursor.getInt(3);
                    data.note = mCursor.getString(4);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<Sale> getAllSale() {
        Cursor mCursor = null;
        List<Sale> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_SALE
                    , new String[]{ITEM_ID, PRIVATE_ID, COMPANY_ID, SALE_PRICE, SALE_NOTE}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Sale data = new Sale();
                    data.idItem = mCursor.getInt(0);
                    data.idPrivate = mCursor.getInt(1);
                    data.idCompany = mCursor.getInt(2);
                    data.price = mCursor.getInt(3);
                    data.note = mCursor.getString(4);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public Bill getBill(int id) {
        Cursor mCursor = null;
        Bill data = new Bill();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_BILL
                    , new String[]{BILL_NUMBER, PRIVATE_ID, COMPANY_ID, BILL_DATE, BILL_TOTAL, IS_PAYED, IS_PAY_TYPE, BILL_NOTE}
                    , BILL_NUMBER + " = " + id, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    data.number = mCursor.getInt(0);
                    data.idPrivate = mCursor.getInt(1);
                    data.idCompany = mCursor.getInt(2);
                    data.date = mCursor.getInt(3);
                    data.total = mCursor.getInt(4);
                    data.payed = mCursor.getInt(5);
                    data.payType = mCursor.getInt(6);
                    data.note = mCursor.getString(7);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public List<Bill> getAllBill() {
        Cursor mCursor = null;
        List<Bill> list = new ArrayList<>();
        try {
            mCursor = mDB.query(true, DATABASE_TABLE_BILL
                    , new String[]{BILL_NUMBER, PRIVATE_ID, COMPANY_ID, BILL_DATE, BILL_TOTAL, IS_PAYED, IS_PAY_TYPE, BILL_NOTE}
                    , null, null, null, null, null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Bill data = new Bill();
                    data.number = mCursor.getInt(0);
                    data.idPrivate = mCursor.getInt(1);
                    data.idCompany = mCursor.getInt(2);
                    data.date = mCursor.getInt(3);
                    data.total = mCursor.getInt(4);
                    data.payed = mCursor.getInt(5);
                    data.payType = mCursor.getInt(6);
                    data.note = mCursor.getString(7);
                    list.add(data);
                } while (mCursor.moveToNext());
            }
        } finally {
            mCursor.close();
        }
        return list;
    }

    public void update(String table, String column, Object value) {
        boolean ss = false;
        while (!ss) {
            try {
                mDB.beginTransaction();
                ss = true;
            } catch (Exception e) {
                mDB = mDBHelper.getReadableDatabase();
            }
        }
        try {
            if (value instanceof String) {
                stringValue = (String) value;
                mDB.execSQL("UPDATE " + table + " SET " + column + " = '" + stringValue + "'");
            } else {
                intValue = (int) value;
                mDB.execSQL("UPDATE " + table + " SET " + column + " = " + intValue + "");
            }
            mDB.setTransactionSuccessful();
            Log.d(Tag.Database, "Update success");
        } catch (Exception e) {
            mDB = mDBHelper.getReadableDatabase();
            Log.d(Tag.Database, "Update fail");
        } finally {
            mDB.endTransaction();
        }
    }

    public void delete(String table, String column, Object value) {
        boolean ss = false;
        while (!ss) {
            try {
                mDB.beginTransaction();
                ss = true;
            } catch (Exception e) {
                mDB = mDBHelper.getReadableDatabase();
            }
        }
        try {
            if (value instanceof String) {
                stringValue = (String) value;
                mDB.execSQL("DELETE FROM " + table
                        + " WHERE " + column + " = \"" + stringValue + "\"");
            } else {
                intValue = (int) value;
                mDB.execSQL("DELETE FROM " + table
                        + " WHERE " + column + " = " + intValue + "");
            }

            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            mDB = mDBHelper.getReadableDatabase();
        } finally {
            mDB.endTransaction();
        }
    }
}