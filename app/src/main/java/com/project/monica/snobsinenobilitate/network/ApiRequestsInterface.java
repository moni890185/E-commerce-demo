package com.project.monica.snobsinenobilitate.network;

import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by monica on 24/02/2015.
 */
public interface ApiRequestsInterface {

  /**
   * Category Request Interface
   *
   */
  // 5 mins cached
  @Headers("Cache-Control:public, max-age=300")
  @GET("/products") void getCategoryProductsAsync(@Query("format") String json, @Query("pid") String apiKey,@Query("cat") String category,
      Callback<ProductList> productResponseCallback);

  @GET("/products") Response getCategoryProductsSync(@Query("format") String json, @Query("pid") String apiKey,@Query("cat") String category);

  //@GET("/products?")
  //public Category getCategoryProductsSyncWithDynamicHeader(@Header(ApiConstants.HEADER_CACHE_CONTROL) String cacheControlValue, @Query("format") String json, @Query("pid") String apiKey,@Query("cat") String category);


  /**
   * Product Detail Request Interface
   *
   */
  //@GET("/products/{productId}?") void getProductList( @Path("productId") productId,@Query("format") String json, @Query("pid") String apiKey,Callback<Product> productResponseCallback);
}
