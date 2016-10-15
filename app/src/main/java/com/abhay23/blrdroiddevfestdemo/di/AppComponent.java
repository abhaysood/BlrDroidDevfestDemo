package com.abhay23.blrdroiddevfestdemo.di;

import com.abhay23.blrdroiddevfestdemo.model.manager.ContactsManager;
import com.abhay23.blrdroiddevfestdemo.model.manager.HttpErrorManager;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class }) public interface AppComponent {
  ContactsManager provideContactsManager();
  
  HttpErrorManager provideApiErrorManager();
}
