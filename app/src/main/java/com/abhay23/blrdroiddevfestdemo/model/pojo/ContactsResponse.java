package com.abhay23.blrdroiddevfestdemo.model.pojo;

import java.util.List;

public class ContactsResponse {

  List<Contact> contacts;

  public ContactsResponse() {
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }
}
