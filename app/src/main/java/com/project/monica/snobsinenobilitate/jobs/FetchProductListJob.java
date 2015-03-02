package com.project.monica.snobsinenobilitate.jobs;

import android.content.Context;
import com.path.android.jobqueue.Params;
import com.project.monica.snobsinenobilitate.database.dao.ProductListDAO;
import com.project.monica.snobsinenobilitate.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.events.ProductListContentEvent;
import com.project.monica.snobsinenobilitate.models.DBModel;
import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import com.project.monica.snobsinenobilitate.network.JacksonConverter;
import com.project.monica.snobsinenobilitate.otto.BusProvider;
import com.project.monica.snobsinenobilitate.utils.Logger;
import retrofit.client.Response;

/**
 * Created by monica on 26/02/2015.
 */
public class FetchProductListJob extends BaseJob {
  public static final int PRIORITY = 99;
  private String mCategoryCode;

  public FetchProductListJob(String categoryProduct, String url, String cacheKey, Context context) {
    super(new Params(PRIORITY), url, cacheKey, context);
    mCategoryCode = categoryProduct;
  }

  @Override public void onAdded() {
    Logger.d("onAdded");
  }

  @Override
  protected Object getDataFromDB() {
    Logger.d("FetchProductListJob - getDataFromDB");
    ProductList productListCached = null;
    // request to the DB
    ProductListDAO productListDAO = DBModel.getInstance(getContext()).getProductListDAO();

    productListCached = productListDAO.getProductList(getContext(), mCategoryCode, null);
    Logger.d("data from DB size : " + productListCached.getProducts().size());
    if (productListCached != null) {
      BusProvider.getInstance().post(new ProductListContentEvent(productListCached));
    } else {
      //todo
      BusProvider.getInstance().post(new NetworkErrorEvent());
    }

    return productListCached;
  }

  @Override
  protected Response getDataFromNetwork() {
    Logger.d("FetchProductListJob - getDataFromNetwork");
    // launching the network request for this specific Job!!!
    ProductList productList = null;
    Response response = null;
    JacksonConverter jsonConverter = new JacksonConverter();
    try {
      //response = ApiModel.getInstance().getCategoryProductsFromSyncRequest(mCategoryCode);
      productList = (ProductList)jsonConverter.fromBody(response.getBody(),ProductList.class);
      Logger.d("FetchProductListJob - getDataFromNetwork - productList retrieved "+ productList.toString());
      // add the product list in the DB.
      DBModel.getInstance(getContext()).getProductListDAO().addProductList(getContext(),
          productList);

      BusProvider.getInstance().post(new ProductListContentEvent(productList));
      //TODO here i should retrieve also the info regarding the max Age and insert them inside the db and update it.

    } catch (Exception e) {

    }
    return response;
  }

  @Override protected void onCancel() {
    Logger.d("onCancel");
  }

  @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {

    Logger.d("shouldReRunOnThrowable");
    return false;
  }
}
