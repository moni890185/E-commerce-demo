package com.project.monica.snobsinenobilitate.models;

import com.project.monica.snobsinenobilitate.bus.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.bus.events.ProductDetailContentEvent;
import com.project.monica.snobsinenobilitate.bus.events.ProductListContentEvent;
import com.project.monica.snobsinenobilitate.models.pojo.Product;
import com.project.monica.snobsinenobilitate.models.pojo.ProductList;
import com.project.monica.snobsinenobilitate.models.rest.ApiRequestsInterface;
import com.project.monica.snobsinenobilitate.models.rest.ApiUtils;
import com.project.monica.snobsinenobilitate.models.rest.JacksonConverter;
import com.project.monica.snobsinenobilitate.bus.BusProvider;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
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
        .setEndpoint(ApiUtils.BASE_URL)
        .setConverter(new JacksonConverter())
        .setClient(getHttpClient())
        .build();

    mRetailerService = restAdapter.create(ApiRequestsInterface.class);
  }

  private OkClient getHttpClient() {

    OkHttpClient httpClient = new OkHttpClient();
    httpClient.setConnectTimeout(ApiUtils.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
    httpClient.setReadTimeout(ApiUtils.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
    Cache responseCache = null;
    try {
      File cacheDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
      responseCache = new Cache(cacheDir, ApiUtils.HTTP_CLIENT_CACHE_SIZE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (responseCache != null) {
      httpClient.setCache(responseCache);
    }
    return new OkClient(httpClient);
  }

  public void getCategoryProducts(String categoryProducts) {
    mRetailerService.getCategoryProducts(ApiUtils.FORMAT, ApiUtils.API_KEY_PID,
        categoryProducts, new Callback<ProductList>() {
          @Override public void success(ProductList productList, Response response) {
            for (Header h : response.getHeaders()) {
              Logger.d("Response: name: " + h.getName() + " value: " + h.getValue());
            }
            BusProvider.getInstance().post(new ProductListContentEvent(productList));
          }

          @Override public void failure(RetrofitError error) {
            Logger.d("Error response retrofit due to: " + error.getMessage());
            BusProvider.getInstance().post(new NetworkErrorEvent(error));
          }
        });
  }

  public void getProductDetail(String mProductId) {
    mRetailerService.getProductDetail(mProductId, ApiUtils.FORMAT, ApiUtils.API_KEY_PID,
        new Callback<Product>() {
          @Override public void success(Product product, Response response) {
            BusProvider.getInstance().post(new ProductDetailContentEvent(product));
          }

          @Override public void failure(RetrofitError error) {
            BusProvider.getInstance().post(new NetworkErrorEvent(error));
          }
        });
  }
}


