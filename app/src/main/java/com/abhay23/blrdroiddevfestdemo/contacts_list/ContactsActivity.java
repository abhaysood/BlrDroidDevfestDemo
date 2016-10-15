package com.abhay23.blrdroiddevfestdemo.contacts_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.abhay23.blrdroiddevfestdemo.BaseActivity;
import com.abhay23.blrdroiddevfestdemo.R;
import com.abhay23.blrdroiddevfestdemo.di.Injector;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import static android.view.View.GONE;

public class ContactsActivity extends BaseActivity implements ContactsView {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.contacts_recycler_view) FastScrollRecyclerView contactsRecyclerView;
  @Bind(R.id.progress_bar) ProgressBar progressBar;
  @Bind(R.id.error) TextView error;

  @Inject ContactsPresenter contactsPresenter;
  @Inject ContactsAdapter contactsAdapter;
  @Inject LinearLayoutManager linearLayoutManager;

  private static final String SAVE_RECYCLER_VIEW_STATE = "save_recycler_view_state";

  @Override public void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_contacts);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);
  }

  @Override protected void inject(Injector injector) {
    injector.inject(this);
  }

  @Override public void setupContactsView() {
    setTitle("Contact Book");
    setSupportActionBar(toolbar);
    contactsRecyclerView.setLayoutManager(linearLayoutManager);
    contactsRecyclerView.setAdapter(contactsAdapter);
  }

  @Override public void showContactsList(List<Contact> contacts) {
    contactsRecyclerView.setVisibility(View.VISIBLE);
    contactsAdapter.setContacts(contacts);
  }

  @NonNull @Override public ContactsPresenter getPresenter() {
    return contactsPresenter;
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void showNoContactsAvailableError() {
    error.setVisibility(View.VISIBLE);
  }

  @Override public void hideContactsList() {
    contactsRecyclerView.setVisibility(GONE);
  }

  @Override public void hideError() {
    error.setVisibility(GONE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(GONE);
  }

  @Override public boolean isContactListAvailable() {
    if (contactsAdapter != null
        && contactsAdapter.getContacts() != null
        && !contactsAdapter.getContacts().isEmpty()) {
      return true;
    }
    return false;
  }

  @Override public void showNetworkError() {
    Snackbar.make(contactsRecyclerView, "Please check your network connection",
        Snackbar.LENGTH_LONG).show();
  }

  @Override public void notifyContactsUpdated() {
    Snackbar.make(contactsRecyclerView, "Contacts have been updated", Snackbar.LENGTH_LONG).show();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    contactsPresenter.onSaveState(outState);
    if (contactsAdapter != null) {
      ArrayList<Contact> contacts = (ArrayList<Contact>) contactsAdapter.getContacts();
      if (contacts != null && !contacts.isEmpty()) {
        outState.putParcelableArrayList(SAVE_RECYCLER_VIEW_STATE, contacts);
      }
    }
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    contactsPresenter.onRestoreState(savedInstanceState);
    ArrayList<Contact> contacts =
        savedInstanceState.getParcelableArrayList(SAVE_RECYCLER_VIEW_STATE);
    if (contacts != null && !contacts.isEmpty() && contactsAdapter != null) {
      contactsAdapter.setContacts(contacts);
    }
  }

  @Override public void showHttpError(int errorCode) {
    Snackbar.make(contactsRecyclerView, "Error contacting servers, code: " + errorCode,
        Snackbar.LENGTH_LONG).show();
  }
}
