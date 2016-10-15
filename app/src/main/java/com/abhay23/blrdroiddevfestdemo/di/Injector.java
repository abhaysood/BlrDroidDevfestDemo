package com.abhay23.blrdroiddevfestdemo.di;

import android.app.Application;
import com.abhay23.blrdroiddevfestdemo.contacts_list.ContactsActivity;

public interface Injector {
  void init(Application application);

  void inject(ContactsActivity contactsActivity);
}
