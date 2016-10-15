package com.abhay23.blrdroiddevfestdemo.model;

import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface ContactsApi {

  @GET("/.json") Observable<Response<List<Contact>>> getAllContacts();
}
