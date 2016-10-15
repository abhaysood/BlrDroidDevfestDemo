package com.abhay23.blrdroiddevfestdemo.di.contacts_list;

import com.abhay23.blrdroiddevfestdemo.ActivityScope;
import com.abhay23.blrdroiddevfestdemo.contacts_list.ContactsActivity;
import com.abhay23.blrdroiddevfestdemo.di.AppComponent;
import dagger.Component;

@ActivityScope
@Component(modules = { ContactsModule.class }, dependencies = { AppComponent.class })
public interface ContactsComponent {
  void inject(ContactsActivity contactsActivity);
}
