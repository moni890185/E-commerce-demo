package com.project.monica.snobsinenobilitate.database.dao;

import com.project.monica.snobsinenobilitate.models.pojo.RequestMeta;

/**
 * Created by monica on 28/02/2015.
 */
public interface RequestsDAO {
  public void addRequest(String url, String eTag, long lastModified, String locale,
      String responseData, String requestType);
  public int deleteRequest(String url);
  public int deleteRequestsOfType(String type);
  public RequestMeta getRequest(String url);
}
