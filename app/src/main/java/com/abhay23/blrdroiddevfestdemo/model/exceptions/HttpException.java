package com.abhay23.blrdroiddevfestdemo.model.exceptions;

import okhttp3.ResponseBody;

/**
 * Thrown when the response received for any API call is anything other than 200
 */
public class HttpException extends Exception {

  private final int errorCode;
  private final ResponseBody errorBody;

  public HttpException(int errorCode, ResponseBody errorBody) {
    this.errorCode = errorCode;
    this.errorBody = errorBody;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public ResponseBody getErrorBody() {
    return errorBody;
  }
}
