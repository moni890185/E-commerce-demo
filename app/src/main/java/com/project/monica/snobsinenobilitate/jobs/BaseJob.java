package com.project.monica.snobsinenobilitate.jobs;

import android.content.Context;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.project.monica.snobsinenobilitate.database.RequestsDAOImpl;
import com.project.monica.snobsinenobilitate.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.models.DBModel;
import com.project.monica.snobsinenobilitate.models.pojo.RequestMeta;
import com.project.monica.snobsinenobilitate.network.ApiConstants;
import com.project.monica.snobsinenobilitate.otto.BusProvider;
import com.project.monica.snobsinenobilitate.utils.DeveloperConstants;
import com.project.monica.snobsinenobilitate.utils.Logger;
import java.util.List;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by monica on 26/02/2015.
 */
public abstract class BaseJob extends Job {

  protected static final int PRIORITY = 99;
  private String mUrl;
  private String mCacheKey;
  private Context mContext;

  public BaseJob(Params p, String url, String cacheKey, Context context) {
    super(p);
    mUrl = url;
    mCacheKey = cacheKey;
    mContext = context;
  }

  public RequestMeta getRequestFromDB(String url, String cacheKey) {

    RequestMeta request;
    RequestsDAOImpl requestDao = DBModel.getInstance(getContext()).getRequestsDAO();
    if (url != null) {
      request = requestDao.getRequest(url);
    } else {

      request = requestDao.getRequest(cacheKey);
    }
    Logger.d("request: " + request);

    return request;
  }

  @Override public void onRun() throws Throwable {
    Logger.d("onRun BaseJob");
    // Checking if request has been already done - and if content is still valid.
    RequestMeta request = null;
    request = getRequestFromDB(getUrl(), getCacheKey());

    Logger.d("request is in db? " + request + "url: " + getUrl() + " cacheKey :" + getCacheKey());
    getData(request);
  }

  protected void getData(RequestMeta request) {
    Object requestResult;
    Logger.d("getData");

    if (ApiConstants.hasNetworkConnection(getContext())) {

      if (request != null) {
        long maxAge = request.getMaxAge();

        // Check if the max age is bigger than the limit to force a
        // request
        long currentTime = System.currentTimeMillis();
        long timeLeftToExpired = Math.abs(maxAge - currentTime);
        if ((currentTime >= maxAge && maxAge > -1)
            || timeLeftToExpired > ApiConstants.MAX_AGE_LIMIT) {
          if (DeveloperConstants.LOGGING_ENABLED) {
            Logger.d("data has expired, get from network again");
          }

          // Remove expired content and set the request to the new
          // updated one.
          removeExpiredRequest(getContext(), getCacheKey());
          requestResult = getFromNetworkThenDB();
        } else {
          if (DeveloperConstants.LOGGING_ENABLED) {
            Logger.d("data is still fresh, get from disk");
          }

          requestResult = getDataFromDB();
        }
      } else {
        if (DeveloperConstants.LOGGING_ENABLED) {
          Logger.d("data is not on disk, get from network");
        }

        requestResult = getFromNetworkThenDB();
      }
    } else {

      if (DeveloperConstants.LOGGING_ENABLED) {
        Logger.d("no network, try see if its on the disk");
      }

      requestResult = getDataFromDB();

      if (DeveloperConstants.LOGGING_ENABLED) {
        Logger.d("is requestResult null: " + (requestResult == null));
      }
    }

    // No data from the network or disk
    if (requestResult == null) {
      // Check to see if there is no network
      if (!ApiConstants.hasNetworkConnection(getContext())) {
        requestResult = new NetworkErrorEvent();
      }
      BusProvider.getInstance().post(requestResult);
    }
  }

  private void removeExpiredRequest(Context context, String cacheKey) {
    RequestsDAOImpl requestDao = DBModel.getInstance(context).getRequestsDAO();
    requestDao.deleteRequest(cacheKey);
  }

  protected abstract Response getDataFromNetwork();

  protected abstract Object getDataFromDB();

  protected String getUrl() {
    return mUrl;
  }

  protected String getCacheKey() {
    return mCacheKey;
  }

  public Context getContext() {
    return mContext;
  }

  // manage offline scenario as well
  private Object getFromNetworkThenDB() {
    Logger.d("getFromNetworkThenDB");
    Object requestResult = getDataFromNetwork();

    // Success from network and the data is fresh
    if (requestResult != null) {
      cacheSuccessfulRequestMeta(requestResult);
      return requestResult;
    }

    // Failure, try from disk
    requestResult = getDataFromDB();

    // Successfully got from disk
    if (requestResult != null) {
      return requestResult;
    }

    // Not on disk or network
    return null;
  }

  //cacheSuccessfulRequestMeta(context, context.getString(R.string.end_point_update_cart), response,
  //HttpURLConnection.HTTP_OK, TYPE_UPDATE_CART);
  private void cacheSuccessfulRequestMeta(Object response) {
    Response retrofitResponse = (Response) response;
    long maxAge = getCacheControl(retrofitResponse);

    DBModel.getInstance(mContext)
        .getRequestsDAO()
        .addRequest( retrofitResponse.getUrl(),null ,maxAge,null,
            String.valueOf(retrofitResponse.getStatus()), mCacheKey);
  }
  //public void addRequest(String url, String eTag, long lastModified, String locale,
  //    String responseData, String requestType)
  private long getCacheControl(Response response) {
    if (response != null) {
      List<Header> headers = response.getHeaders();
      Header cacheControlHeaders = null;
      if (headers != null) {

        for (Header h : headers) {
          if (h.getName().equals(ApiConstants.HEADER_CACHE_CONTROL)) {
            cacheControlHeaders = h;
          }
        }

        if (cacheControlHeaders.getValue() != null) {


          String[] details = cacheControlHeaders.getValue().split("=");

          if (details.length == 2 && details[0].equals("max-age")) {

            // Check if the max-age is bigger than the limit
            long maxAge = Long.parseLong(details[1]) * 1000;
            if (maxAge > ApiConstants.MAX_AGE_LIMIT) {
              maxAge = ApiConstants.MAX_AGE_LIMIT;
            }

            return System.currentTimeMillis() + maxAge;
          }
        }
      }
    }

    return 0;
  }
}
