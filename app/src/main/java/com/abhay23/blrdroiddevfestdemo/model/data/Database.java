package com.abhay23.blrdroiddevfestdemo.model.data;

import android.content.Context;
import android.support.annotation.Nullable;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import java.util.List;

public class Database {

  DatabaseHelper dbHelper;

  public Database(Context context) {
    dbHelper = new DatabaseHelper(context);
  }

  @Nullable public List<Contact> getAllContacts() {
    return dbHelper.getAllContacts();
  }

  public void saveAllContacts(List<Contact> contacts) {
    dbHelper.overwriteAllContacts(contacts);
  }
}


