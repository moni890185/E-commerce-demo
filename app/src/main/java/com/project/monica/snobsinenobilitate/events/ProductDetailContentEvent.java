package com.project.monica.snobsinenobilitate.events;

import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;

/**
 * Created by monica on 03/03/2015.
 */
public class ProductDetailContentEvent {

  Product mProduct;

  public ProductDetailContentEvent(Product product) {
    this.mProduct = product;
  }

  public Product getProduct()
  {
    return mProduct;
  }
}
