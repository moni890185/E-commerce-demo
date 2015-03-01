package com.project.monica.snobsinenobilitate.database.dao;

import android.content.Context;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import java.util.List;

/**
 * Created by monica on 28/02/2015.
 */
public interface ProductDAO {

  public Product getProduct(Context context, String productCode);
  public List<Product> getProducts(Context context, String[] productCodes);
  public void clearProducts();
  public void addProducts(Context context, List<Product> products);
}
