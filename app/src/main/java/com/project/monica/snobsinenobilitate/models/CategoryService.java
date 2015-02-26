package com.project.monica.snobsinenobilitate.models;

/**
 * Created by monica on 24/02/2015.
 */
public class CategoryService {

  // layer to manage requests and caching.

  private static CategoryService mInstance;

  public static CategoryService getInstance() {
    if (mInstance == null) {
      mInstance = new CategoryService();
    }
    return mInstance;
  }

  public void getCategoryProducts(String productCategory) {

    //getData(productCategory);


  }

  /*
    If you use @Cached, the result is cached before being sent to the receiver(s).
    On next call, receivers will immediately receive the previous cached event, then
    this method is called. Its new result replaces the previous cache, then is sent
    to the receivers.
    The default cache key is <class_name>.<method_name>(<arg1.toString>, <arg2.toString>, ...)
*/


  //private void getData(String productCategory) {
  //if(!isCached) {
  //  launchNetworkRequest(productCategory);
  //}
  //  else
  //{
  //  loadDataFromDB(productCategory);
  //}
  //}
  //
  //private void launchNetworkRequest(String productCategory) {
  //  ApiModuleRequests.getInstance()
  //      .getCategoryProducts( productCategory,  new Callback<Category>() {
  //        @Override public void success(Category category, Response response) {
  //          //Retrofit will download and parse the API data on a background thread, and then deliver the results back here to the UI thread
  //          saveDataInDatabase(category);
  //        }
  //
  //        @Override public void failure(RetrofitError error) {
  //          Logger.d("Monica",
  //              "retrofit error" + error.getUrl() + error.getSuccessType() + error.getMessage());
  //          launchNetworkErrorEvent();
  //        }
  //      });
  //}
  //
  //private void coreConsumeDate(Category category) {
  //
  //  insertInDB();
  //}

}
