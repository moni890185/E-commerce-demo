package com.project.monica.snobsinenobilitate.network;

import com.project.monica.snobsinenobilitate.models.pojo.Category;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by monica on 24/02/2015.
 */
public class ApiModuleRequests {

  private static ApiModuleRequests mApiModule;
  private ApiRequestsInterface mRetailerService;

  public static ApiModuleRequests getInstance() {
    if (mApiModule == null) {
      mApiModule = new ApiModuleRequests();
    }
    return mApiModule;
  }

  private ApiModuleRequests() {
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(ApiConstants.BASE_URL)
        .setConverter(new JacksonConverter())
        .setClient(getHttpClient())
        .build();

    mRetailerService = restAdapter.create(ApiRequestsInterface.class);
  }

  private OkClient getHttpClient() {

    OkHttpClient httpClient = new OkHttpClient();
    httpClient.setConnectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
    httpClient.setReadTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
    return new OkClient(httpClient);
  }

  // Network Requests
  public void getCategoryProducts(String categoryProducts, Callback<Category> productCallback) {
    mRetailerService.getCategoryProducts(ApiConstants.FORMAT, ApiConstants.API_KEY_PID,categoryProducts,productCallback);
  }
}


