package com.project.monica.snobsinenobilitate.utils;

import android.util.Log;

/**
 * Created by monica on 26/02/2015.
 */
public class Logger {

  private static final String TAG = "Monica";


  public static void d(String string) {
    if (DeveloperConstants.LOGGING_ENABLED)
    {
      Log.d(TAG, string);
    }
  }
}
