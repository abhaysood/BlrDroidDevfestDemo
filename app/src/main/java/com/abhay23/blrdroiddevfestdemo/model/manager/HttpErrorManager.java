package com.abhay23.blrdroiddevfestdemo.model.manager;

import com.abhay23.blrdroiddevfestdemo.BaseView;
import com.abhay23.blrdroiddevfestdemo.model.exceptions.HttpException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class HttpErrorManager {

  public boolean handleErrors(BaseView view, Throwable e) {
    if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
      view.showNetworkError();
      return true;
    }

    if (e instanceof HttpException) {
      view.showHttpError(((HttpException) e).getErrorCode());
      return true;
    }
    return false;
  }
}

