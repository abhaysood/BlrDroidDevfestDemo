package com.abhay23.blrdroiddevfestdemo.model.manager;

import android.support.annotation.NonNull;
import com.abhay23.blrdroiddevfestdemo.model.ContactsApi;
import com.abhay23.blrdroiddevfestdemo.model.data.Database;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import com.abhay23.blrdroiddevfestdemo.utils.RxUtils;
import java.util.List;
import rx.Observable;

public class ContactsManager {

  public static String TAG = "ContactsManager";
  private final Database database;
  private final ContactsApi contactsApi;
  private final RxUtils rxUtils;

  public ContactsManager(Database database, ContactsApi contactsApi, RxUtils rxUtils) {
    this.database = database;
    this.contactsApi = contactsApi;
    this.rxUtils = rxUtils;
  }

  public Observable<List<Contact>> loadContacts() {
    Observable<List<Contact>> local = getLocalContactsObservable();
    Observable<List<Contact>> remote = getRemoteContactsObservable();

    return Observable.concatDelayError(local, remote)
        .compose(rxUtils.applySchedulersWithDelayError());
  }

  @NonNull private Observable.Transformer<List<Contact>, List<Contact>> getCleanSortedContacts() {
    return listObservable -> listObservable.flatMap(Observable::from)
        .toSortedList(this::compareContactByFullName);
  }

  private int compareContactByFullName(Contact contact, Contact contact2) {
    return contact.getFullName()
        .trim()
        .toLowerCase()
        .compareTo(contact2.getFullName().trim().toLowerCase());
  }

  @NonNull private Observable<List<Contact>> getRemoteContactsObservable() {
    return contactsApi.getAllContacts().map(new ValidateHTTPResponse<>()).map(contacts -> {
      database.saveAllContacts(contacts);
      return contacts;
    }).compose(getCleanSortedContacts());
  }

  @NonNull private Observable<List<Contact>> getLocalContactsObservable() {
    return Observable.just(database.getAllContacts()).compose(getCleanSortedContacts());
  }
}
