package com.project.monica.snobsinenobilitate.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ApiConstants {
  public static final String BASE_URL = "http://api.shopstyle.com/api/v2";
  public static final int HTTP_CONNECT_TIMEOUT = 6000; // milliseconds
  public static final int HTTP_READ_TIMEOUT = 10000; // milliseconds
  public static final String API_KEY_PID = "uid5609-26903853-26";
  public static final String FORMAT = "json";
  public static final String HEADER_CACHE_CONTROL = "Cache-Control";
  /**
   * Constant to define the limit of max age. If the value is bigger, we need
   * to reset the value to be sure that we request the data again. Defined 12
   * hours.
   */
  public static final long MAX_AGE_LIMIT = 43200000;
  public static String RESPONSE_VALUE_SUCCESS = "success";
  // i.e. http://api.shopstyle.com/api/v2/products?pid=uid5609-26903853-26&format=JSON&cat=day-dresses


  public static boolean hasNetworkConnection(Context context) {
    if (context != null) {
      ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

      if (activeNetwork != null) {
        return activeNetwork.isConnectedOrConnecting();
      }
    }
    return false;
  }
}