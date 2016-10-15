package com.abhay23.blrdroiddevfestdemo.contacts_list;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.abhay23.blrdroiddevfestdemo.BasePresenter;
import com.abhay23.blrdroiddevfestdemo.model.manager.ContactsManager;
import com.abhay23.blrdroiddevfestdemo.model.manager.HttpErrorManager;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import java.util.List;
import rx.Subscription;

public class ContactsPresenter extends BasePresenter {

  public static String TAG = "ContactsPresenter";
  private final String SAVE_PAGE_STATE = "SAVE_PAGE_STATE";
  private final ContactsView view;
  private final ContactsManager contactsManager;
  private final HttpErrorManager httpErrorManager;

  @VisibleForTesting PageState pageState;

  enum PageState {
    PROGRESS,
    SHOWING_CONTACTS,
    ERROR
  }

  public PageState getPageState() {
    return pageState;
  }

  public void setPageState(PageState pageState) {
    this.pageState = pageState;
  }

  public ContactsPresenter(ContactsView view, ContactsManager contactsManager,
      HttpErrorManager httpErrorManager) {
    super(view);
    this.view = view;
    this.contactsManager = contactsManager;
    this.httpErrorManager = httpErrorManager;
  }

  @Override public void onViewCreated(boolean isNewLaunch) {
    view.setupContactsView();
    setPageState(PageState.SHOWING_CONTACTS);

    if (isNewLaunch) {
      showProgress();

      Subscription subscription = contactsManager.loadContacts()
          .subscribe(this::onContactsLoaded, this::handleContactsLoadingErrors);
      addSubscription(subscription);
    }
  }

  private void onContactsLoaded(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      showNoContactsAvailableError();
      return;
    }

    if (view.isContactListAvailable()) {
      view.notifyContactsUpdated();
    }

    showContactsList(contacts);
  }

  private void handleContactsLoadingErrors(Throwable e) {
    // Handle general API errors
    boolean isErrorHandled = httpErrorManager.handleErrors(view, e);

    if (isErrorHandled) {
      view.hideProgress();
      if (!view.isContactListAvailable()) {
        showNoContactsAvailableError();
      }
      return;
    }

    // Unhandled errors
    throw new IllegalStateException(e);
  }

  private void showProgress() {
    setPageState(PageState.PROGRESS);
    view.showProgress();
    view.hideContactsList();
    view.hideError();
  }

  private void showContactsList(List<Contact> contacts) {
    setPageState(PageState.SHOWING_CONTACTS);
    view.showContactsList(contacts);
    view.hideError();
    view.hideProgress();
  }

  private void showNoContactsAvailableError() {
    setPageState(PageState.ERROR);
    view.showNoContactsAvailableError();
    view.hideProgress();
    view.hideContactsList();
  }

  public void onSaveState(Bundle outState) {
    Log.d(TAG, "onSaveState" + getPageState().name());
    outState.putString(SAVE_PAGE_STATE, getPageState().name());
  }

  public void onRestoreState(Bundle savedInstanceState) {
    if (savedInstanceState.get(SAVE_PAGE_STATE) != null) {
      setPageState(PageState.valueOf((String) savedInstanceState.get(SAVE_PAGE_STATE)));
      Log.d(TAG, "onRestoreState" + getPageState().name());
    }

    switch (getPageState()) {
      case PROGRESS:
        showProgress();
        break;
      case ERROR:
        showNoContactsAvailableError();
        break;
      case SHOWING_CONTACTS:
        break;
    }
  }
}
