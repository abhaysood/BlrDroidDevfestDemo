package com.abhay23.blrdroiddevfestdemo;

import android.app.Application;
import com.abhay23.blrdroiddevfestdemo.di.DaggerInjector;
import com.abhay23.blrdroiddevfestdemo.di.Injector;

public class ContactsApplication extends Application {

  private Injector daggerInjector;

  public void initDagger() {
    getDaggerInjector().init(this);
  }

  public Injector getDaggerInjector() {
    if (daggerInjector == null) {
      daggerInjector = new DaggerInjector();
    }
    return daggerInjector;
  }

  @Override public void onCreate() {
    super.onCreate();
    initDagger();
  }
}
