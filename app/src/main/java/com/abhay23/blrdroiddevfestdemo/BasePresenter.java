package com.abhay23.blrdroiddevfestdemo;

import android.support.annotation.CallSuper;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {
  private final BaseView view;
  private final CompositeSubscription compositeSubscription;
  public static String TAG = "BasePresenter";

  public BasePresenter(BaseView view) {
    this.view = view;
    compositeSubscription = new CompositeSubscription();
  }

  @CallSuper protected void unsubscribe() {
    compositeSubscription.clear();
  }

  protected void addSubscription(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public abstract void onViewCreated(boolean isNewLaunch);
}
