package com.project.monica.snobsinenobilitate.models;

import android.content.Context;
import com.project.monica.snobsinenobilitate.database.DatabaseHelper;
import com.project.monica.snobsinenobilitate.database.ProductDAOImpl;
import com.project.monica.snobsinenobilitate.database.ProductListDAOImpl;
import com.project.monica.snobsinenobilitate.database.RequestsDAOImpl;
import com.project.monica.snobsinenobilitate.database.SSNDatabase;

/**
 * Created by monica on 28/02/2015.
 */
public class DBModel {
  private final Context mContext;

  private static DBModel mInstance;

  public static DBModel getInstance(Context context) {
    if (mInstance == null) {
      mInstance = new DBModel(context);
    }
    return mInstance;
  }

  ProductListDAOImpl mProductListDao;

  RequestsDAOImpl mRequestsDao;

  ProductDAOImpl mProductDao;

  private DBModel(Context context) {
    mContext = context;
    mDatabase = new SSNDatabase();
    mProductListDao = new ProductListDAOImpl(mDatabase);
    mProductDao = new ProductDAOImpl(mDatabase);
    mRequestsDao = new RequestsDAOImpl(mDatabase);
    DatabaseHelper[] helpers = new DatabaseHelper[] {mProductListDao,mProductDao,mRequestsDao};

    mDatabase.setHelpers(mContext,helpers);
  }

  private SSNDatabase mDatabase;

  public SSNDatabase getDatabase() {
    return mDatabase;
  }

  public RequestsDAOImpl getRequestsDAO()
  {
    return mRequestsDao;
  }

  public ProductDAOImpl getProductDAO()
  {
    return  mProductDao;
  }

  public ProductListDAOImpl getProductListDAO()
  {
    return  mProductListDao;
  }
}
