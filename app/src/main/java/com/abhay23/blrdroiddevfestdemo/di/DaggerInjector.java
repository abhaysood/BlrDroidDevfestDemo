package com.abhay23.blrdroiddevfestdemo.di;

import android.app.Application;
import com.abhay23.blrdroiddevfestdemo.ContactsApplication;
import com.abhay23.blrdroiddevfestdemo.contacts_list.ContactsActivity;
import com.abhay23.blrdroiddevfestdemo.di.contacts_list.ContactsModule;
import com.abhay23.blrdroiddevfestdemo.contacts_list.DaggerContactsComponent;

public class DaggerInjector implements Injector {
  private AppComponent appComponent;

  @Override public void init(Application application) {
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule((ContactsApplication) application))
        .build();
  }

  @Override public void inject(ContactsActivity contactsActivity) {
    DaggerContactsComponent.builder()
        .appComponent(appComponent)
        .contactsModule(new ContactsModule(contactsActivity))
        .build()
        .inject(contactsActivity);
  }
}
