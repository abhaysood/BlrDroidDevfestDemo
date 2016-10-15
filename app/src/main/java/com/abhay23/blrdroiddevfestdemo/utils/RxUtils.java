package com.abhay23.blrdroiddevfestdemo.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Provides helper methods to make working with RxAndroid easier.
 */
public class RxUtils {

    /**
     * Simple transformer that will take an observable and
     * 1. Schedule it on an IO thread
     * 2. Observe on main thread
     * <p>
     * This is to be used with all observables to reduce boilerplate.
     */
    public <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    public <T> Observable.Transformer<T, T> applySchedulersWithDelayError() {
        return observable -> observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true);
    }
}
