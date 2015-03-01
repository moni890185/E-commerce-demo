package com.project.monica.snobsinenobilitate.models;

import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import com.project.monica.snobsinenobilitate.network.ApiConstants;
import com.project.monica.snobsinenobilitate.network.ApiRequestsInterface;
import com.project.monica.snobsinenobilitate.network.JacksonConverter;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by monica on 24/02/2015.
 */
public class ApiModel {

  private static ApiModel mApiModule;
  private ApiRequestsInterface mRetailerService;

  public static ApiModel getInstance() {
    if (mApiModule == null) {
      mApiModule = new ApiModel();
    }
    return mApiModule;
  }

  private ApiModel() {
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

  // Network Requests Category

  public Response getCategoryProductsFromSyncRequest(String productCategory) {
    Response response;
    try {
      response = getCategoryProductsSync(productCategory);
    } catch (RetrofitError e) {
      response = null;
      Logger.d("request null -retrofit error: "+e.getMessage() +" URL: "+ e.getUrl());
    }
    return response;
  }

  public Response getCategoryProductsSync(String categoryProducts) {
    return mRetailerService.getCategoryProductsSync(ApiConstants.FORMAT, ApiConstants.API_KEY_PID,
        categoryProducts);
  }

  public void getCategoryProductsAsync(String categoryProducts,
      Callback<ProductList> productCallback) {
    mRetailerService.getCategoryProductsAsync(ApiConstants.FORMAT, ApiConstants.API_KEY_PID,
        categoryProducts, productCallback);
  }
}


