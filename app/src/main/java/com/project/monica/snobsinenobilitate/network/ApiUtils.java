package com.project.monica.snobsinenobilitate.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ApiUtils {
  public static final long HTTP_CLIENT_CACHE_SIZE = 10 * 1024 * 1024;
  public static final String BASE_URL = "http://api.shopstyle.com/api/v2";
  public static final int HTTP_CONNECT_TIMEOUT = 6000; // milliseconds
  public static final int HTTP_READ_TIMEOUT = 10000; // milliseconds
  public static final String API_KEY_PID = "uid5609-26903853-26";
  public static final String FORMAT = "json";
  public static final String HEADER_CACHE_CONTROL = "Cache-Control";

  public static boolean hasNetworkConnection(Context context) {
    if (context != null) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

      if (activeNetwork != null) {
        return activeNetwork.isConnectedOrConnecting();
      }
    }
    return false;
  }
}