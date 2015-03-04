package com.project.monica.snobsinenobilitate.network;

import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by monica on 24/02/2015.
 */
public interface ApiRequestsInterface {

  // Product List
  @Headers("Cache-Control:public, max-age=1800")
  @GET("/products") void getCategoryProducts(@Query("format") String json,
      @Query("pid") String apiKey, @Query("cat") String category,
      Callback<ProductList> productResponseCallback);

  // Product Detail
  @Headers("Cache-Control:public, max-age=1800")
  @GET("/products/{productId}?") void getProductDetail(@Path("productId") String productId,
      @Query("format") String json, @Query("pid") String apiKey,
      Callback<Product> productResponseCallback);
}
