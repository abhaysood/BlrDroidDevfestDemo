package com.abhay23.blrdroiddevfestdemo.contacts_list;

import com.abhay23.blrdroiddevfestdemo.BaseView;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import java.util.List;

public interface ContactsView extends BaseView {
  void showContactsList(List<Contact> contacts);

  void setupContactsView();

  void showProgress();

  void showNoContactsAvailableError();

  void hideContactsList();

  void hideProgress();

  void hideError();

  boolean isContactListAvailable();

  void showNetworkError();

  void notifyContactsUpdated();

  void showHttpError(int errorBody);
}
