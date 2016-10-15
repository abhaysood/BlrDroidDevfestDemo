package com.abhay23.blrdroiddevfestdemo.di;

import android.content.Context;
import android.net.ConnectivityManager;
import com.abhay23.blrdroiddevfestdemo.ContactsApplication;
import com.abhay23.blrdroiddevfestdemo.model.ContactsApi;
import com.abhay23.blrdroiddevfestdemo.model.data.Database;
import com.abhay23.blrdroiddevfestdemo.model.manager.ContactsManager;
import com.abhay23.blrdroiddevfestdemo.model.manager.HttpErrorManager;
import com.abhay23.blrdroiddevfestdemo.utils.RxUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class AppModule {

  private final ContactsApplication application;
  private static final String BASE_API_URL = "https://sonorous-seat-708.firebaseio.com";

  public AppModule(ContactsApplication application) {
    this.application = application;
  }

  @Provides @Singleton public Database provideDatabase() {
    return new Database(application);
  }

  @Provides @Singleton public RxUtils provideRxUtils() {
    return new RxUtils();
  }

  @Provides @Singleton public ContactsApi provideContactsApiService(Retrofit retrofit) {
    return retrofit.create(ContactsApi.class);
  }

  @Provides @Singleton public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  @Provides @Singleton
  public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
    return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
  }

  @Provides @Singleton public HttpLoggingInterceptor provideInterceptor() {
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Provides @Singleton public Gson provideGson() {
    return new GsonBuilder().create();
  }

  @Provides @Singleton public ConnectivityManager provideConnectivityManager() {
    return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
  }

  @Provides @Singleton
  public ContactsManager provideContactsManager(Database database, ContactsApi contactsApi,
      RxUtils rxUtils) {
    return new ContactsManager(database, contactsApi, rxUtils);
  }

  @Provides @Singleton public HttpErrorManager provideApiErrorManager() {
    return new HttpErrorManager();
  }
}
