package com.project.monica.snobsinenobilitate.bus.events;

import com.project.monica.snobsinenobilitate.models.pojo.ProductList;

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
