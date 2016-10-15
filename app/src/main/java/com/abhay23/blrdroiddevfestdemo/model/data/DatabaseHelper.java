package com.abhay23.blrdroiddevfestdemo.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;

  private static final String DATABASE_NAME = "gojek_contacts.db";

  private static final String TABLE_CONTACTS = "contacts";

  private static final String KEY_ID = "id";
  private static final String KEY_FIRST_NAME = "first_name";
  private static final String KEY_LAST_NAME = "last_name";
  private static final String KEY_PROFILE_PIC = "profile_pic";
  private static final String KEY_URL = "url";
  private static final String KEY_PHONE_NUMBER = "phone_number";
  private static final String KEY_EMAIL = "email";
  private static final String KEY_FAVORITE = "favorite";
  private static final String KEY_CREATED_AT = "created_at";
  private static final String KEY_UPDATED_AT = "updated_at";

  DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACTS_TABLE = "CREATE TABLE "
        + TABLE_CONTACTS
        + "("
        + KEY_ID
        + " INTEGER PRIMARY KEY,"
        + KEY_FIRST_NAME
        + " TEXT,"
        + KEY_LAST_NAME
        + " TEXT,"
        + KEY_PROFILE_PIC
        + " TEXT,"
        + KEY_URL
        + " TEXT,"
        + KEY_PHONE_NUMBER
        + " TEXT,"
        + KEY_EMAIL
        + " TEXT,"
        + KEY_FAVORITE
        + " INTEGER,"
        + KEY_CREATED_AT
        + " INTEGER,"
        + KEY_UPDATED_AT
        + " INTEGER)";

    db.execSQL(CREATE_CONTACTS_TABLE);
  }

  // Upgrading database
  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    // Create tables again
    onCreate(db);
  }

  List<Contact> getAllContacts() {
    List<Contact> contactList = new ArrayList<>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setFirstName(cursor.getString(1));
        contact.setLastName(cursor.getString(2));
        contact.setProfilePic(cursor.getString(3));
        contact.setUrl(cursor.getString(4));
        contact.setPhoneNumber(cursor.getString(5));
        contact.setEmail(cursor.getString(6));
        contact.setFavorite(getBoolean(cursor.getInt(7)));
        contact.setCreatedAt(getDate(cursor.getLong(8)));
        contact.setUpdatedAt(getDate(cursor.getLong(9)));
        // Adding contact to list
        contactList.add(contact);
      } while (cursor.moveToNext());
    }

    cursor.close();

    // return contact list
    return contactList;
  }

  private Date getDate(long date) {
    return new Date(date);
  }

  private boolean getBoolean(int i) {
    return i != 0;
  }

  void overwriteAllContacts(List<Contact> contacts) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.beginTransaction();
    try {
      db.delete(TABLE_CONTACTS, null, null);
      ContentValues values = new ContentValues();
      for (Contact contact : contacts) {
        values.put(KEY_ID, contact.getId());
        values.put(KEY_FIRST_NAME, contact.getFirstName());
        values.put(KEY_LAST_NAME, contact.getLastName());
        values.put(KEY_PROFILE_PIC, contact.getProfilePic());
        values.put(KEY_URL, contact.getUrl());
        values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_FAVORITE, contact.getFavorite());
        values.put(KEY_CREATED_AT, getDateInLong(contact.getCreatedAt()));
        values.put(KEY_UPDATED_AT, getDateInLong(contact.getUpdatedAt()));

        db.insert(TABLE_CONTACTS, null, values);
      }
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
  }

  private long getDateInLong(Date createdAt) {
    if (createdAt == null) {
      return 0;
    }
    return createdAt.getTime();
  }
}