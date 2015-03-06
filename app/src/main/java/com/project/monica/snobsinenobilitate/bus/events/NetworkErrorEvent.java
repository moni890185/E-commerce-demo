package com.project.monica.snobsinenobilitate.bus.events;

import retrofit.RetrofitError;

/**
 * Created by monica on 26/02/2015.
 */
public class NetworkErrorEvent {
  RetrofitError error;

  public NetworkErrorEvent(RetrofitError error) {
    this.error = error;
  }

  public RetrofitError getError()
  {
    return error;
  }
}
