package com.project.monica.snobsinenobilitate.network;

import com.project.monica.snobsinenobilitate.models.pojo.Category;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by monica on 24/02/2015.
 */
public interface ApiRequestsInterface {


  @GET("/products?") void getCategoryProducts(@Query("format") String json, @Query("pid") String apiKey,@Query("cat") String category,
      Callback<Category> productResponseCallback);


  //@GET("/products/{productId}?") void getCategoryProducts( @Path("productId") productId,@Query("format") String json, @Query("pid") String apiKey,Callback<Product> productResponseCallback);
}
