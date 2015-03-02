package com.project.monica.snobsinenobilitate.models;

import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import com.project.monica.snobsinenobilitate.network.ApiConstants;
import com.project.monica.snobsinenobilitate.network.ApiRequestsInterface;
import com.project.monica.snobsinenobilitate.network.JacksonConverter;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by monica on 24/02/2015.
 */
public class ApiModel {

  private static final long HTTP_CLIENT_CACHE_SIZE = 10 * 1024* 1024;
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
        .setRequestInterceptor(new RequestInterceptor() {
          @Override
          public void intercept(RequestFacade request) {
            request.addHeader("Accept", "application/json;versions=1");

              int maxAge = 300; // read from cache for 5 minute
              request.addHeader("Cache-Control", "public, max-age=" + maxAge);

          }
        })
        .build();

          mRetailerService=restAdapter.create(ApiRequestsInterface.class);
        }

  private OkClient getHttpClient() {

    OkHttpClient httpClient = new OkHttpClient();
    httpClient.setConnectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
    httpClient.setReadTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
    Cache responseCache = null;
    try {
      File cacheDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
      responseCache = new Cache(cacheDir, HTTP_CLIENT_CACHE_SIZE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (responseCache != null) {
      httpClient.setCache(responseCache);
    }
    return new OkClient(httpClient);
  }

  // Network Requests Category

  //public Response getCategoryProductsFromSyncRequest(String productCategory) {
  //  Response response;
  //  try {
  //    response = getCategoryProductsSync(productCategory);
  //  } catch (RetrofitError e) {
  //    response = null;
  //    Logger.d("request null -retrofit error: "+e.getMessage() +" URL: "+ e.getUrl());
  //  }
  //  return response;
  //}
  //
  //public Response getCategoryProductsSync(String categoryProducts) {
  //  return mRetailerService.getCategoryProductsSync(ApiConstants.FORMAT, ApiConstants.API_KEY_PID,
  //      categoryProducts);
  //}

  public void getCategoryProductsAsync(String categoryProducts,
      Callback<ProductList> productCallback) {
    mRetailerService.getCategoryProductsAsync(ApiConstants.FORMAT, ApiConstants.API_KEY_PID,
        categoryProducts, productCallback);
  }
}


