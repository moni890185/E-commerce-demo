package com.project.monica.snobsinenobilitate.models;

import android.content.Context;
import com.path.android.jobqueue.JobManager;
import com.project.monica.snobsinenobilitate.application.SSNApplication;
import com.project.monica.snobsinenobilitate.jobs.FetchProductListJob;

/**
 * Created by monica on 26/02/2015.
 */
public class ProductListModel {

  private static ProductListModel mInstance;

  public static ProductListModel getInstance() {
    if (mInstance == null) {
      mInstance = new ProductListModel();
    }
    return mInstance;
  }

  public static class CacheKey {
    public static final String GET_PRODUCT_LIST = "get_product_list";
  }

  private ProductListModel() {
  }

  public void getCategoryProduct(String productListCategoryCode, Context context) {
    JobManager jobManager = ((SSNApplication) context.getApplicationContext()).getJobManager();
    jobManager.addJobInBackground(
        new FetchProductListJob(productListCategoryCode, null, CacheKey.GET_PRODUCT_LIST, context));
  }
}
