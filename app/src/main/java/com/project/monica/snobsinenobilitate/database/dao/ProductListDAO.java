package com.project.monica.snobsinenobilitate.database.dao;

import android.content.Context;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import java.util.List;

/**
 * Created by monica on 28/02/2015.
 */
public interface ProductListDAO {

  public ProductList getProductList(Context context, String categoryCode, String sort);
  public List<Product> getProducts(Context context, String[] productIds, String sort);
  public void addProductList(Context context, ProductList productList);
  public int clearProductList(String... deleteKeys);
  public void addProducts(Context context, List<Product> products);
}
