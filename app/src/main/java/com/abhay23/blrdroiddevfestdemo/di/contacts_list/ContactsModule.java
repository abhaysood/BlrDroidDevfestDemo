package com.abhay23.blrdroiddevfestdemo.di.contacts_list;

import android.support.v7.widget.LinearLayoutManager;
import com.abhay23.blrdroiddevfestdemo.di.ActivityScope;
import com.abhay23.blrdroiddevfestdemo.contacts_list.ContactsActivity;
import com.abhay23.blrdroiddevfestdemo.contacts_list.ContactsAdapter;
import com.abhay23.blrdroiddevfestdemo.contacts_list.ContactsPresenter;
import com.abhay23.blrdroiddevfestdemo.model.manager.ContactsManager;
import com.abhay23.blrdroiddevfestdemo.model.manager.HttpErrorManager;
import dagger.Module;
import dagger.Provides;

@Module public class ContactsModule {

  private final ContactsActivity contactsActivity;

  public ContactsModule(ContactsActivity contactsActivity) {
    this.contactsActivity = contactsActivity;
  }

  @ActivityScope @Provides ContactsPresenter provideContactsPresenter(
      ContactsManager contactsManager, HttpErrorManager httpErrorManager) {
    return new ContactsPresenter(contactsActivity, contactsManager, httpErrorManager);
  }

  @ActivityScope @Provides LinearLayoutManager provideLinearLayoutManager() {
    return new LinearLayoutManager(contactsActivity, LinearLayoutManager.VERTICAL, false);
  }

  @ActivityScope @Provides ContactsAdapter provideContactsAdapter() {
    return new ContactsAdapter();
  }
}
