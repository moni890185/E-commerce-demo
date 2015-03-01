package com.project.monica.snobsinenobilitate.events;

import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;

/**
 * Created by monica on 25/02/2015.
 */
public class ProductListContentEvent {

  private ProductList mProductList;

  public ProductListContentEvent(ProductList productList)
  {
    mProductList = productList;
  }

  public ProductList getProductList()
  {
    return mProductList;
  }

}
