package com.abhay23.blrdroiddevfestdemo.model.manager;

import com.abhay23.blrdroiddevfestdemo.model.exceptions.HttpException;
import retrofit2.Response;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

public class ValidateHTTPResponse<T> implements Func1<Response<T>, T> {

  @Override public T call(Response<T> response) {
    if (response.isSuccessful()) {
      return response.body();
    }

    throw Exceptions.propagate(new HttpException(response.code(), response.errorBody()));
  }
}