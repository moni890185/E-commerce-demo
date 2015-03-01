package com.project.monica.snobsinenobilitate.database;

/**
 * Created by monica on 26/02/2015.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.project.monica.snobsinenobilitate.database.dao.RequestsDAO;
import com.project.monica.snobsinenobilitate.models.pojo.RequestMeta;

public class RequestsDAOImpl extends DatabaseHelper implements RequestsDAO{

  public static class RequestColumns extends DatabaseHelperColumns {
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_MAX_AGE = "max_age";
    private static final String COLUMN_E_TAG = "etag";
    private static final String COLUMN_LOCALE = "locale";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_REQUEST_TYPE = "request_type";
  }

  private static final String TAG = RequestsDAOImpl.class.getSimpleName();
  private static final String TABLE_NAME = "requests";

  public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
      + //
      RequestColumns.COLUMN_ID + RequestColumns.TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT"
      + RequestColumns.COMMA_SEP + //
      RequestColumns.COLUMN_URL + RequestColumns.TYPE_TEXT + RequestColumns.COMMA_SEP + //
      RequestColumns.COLUMN_MAX_AGE + RequestColumns.TYPE_TEXT + RequestColumns.COMMA_SEP + //
      RequestColumns.COLUMN_LOCALE + RequestColumns.TYPE_INTEGER + RequestColumns.COMMA_SEP + //
      RequestColumns.COLUMN_DATA + RequestColumns.TYPE_TEXT + RequestColumns.COMMA_SEP + //
      RequestColumns.COLUMN_REQUEST_TYPE + RequestColumns.TYPE_TEXT + RequestColumns.COMMA_SEP + //
      RequestColumns.COLUMN_E_TAG + RequestColumns.TYPE_TEXT + //
      " )";

  public RequestsDAOImpl(SSNDatabase database) {
    super(database);
  }

  public void clearRequests() {
    getDatabase().clearDataFromTable(TABLE_NAME);
  }

  public void addRequest(String url, String eTag, long lastModified, String locale,
      String responseData, String requestType) {
    // Remove the old row from the table if it exists.
    String whereClause = RequestColumns.COLUMN_URL + "=?";
    String[] whereArgs = { url };
    getDatabase().delete(TABLE_NAME, whereClause, whereArgs);

    ContentValues values = new ContentValues();
    values.put(RequestColumns.COLUMN_URL, url);
    values.put(RequestColumns.COLUMN_E_TAG, eTag);
    values.put(RequestColumns.COLUMN_MAX_AGE, lastModified + "");
    values.put(RequestColumns.COLUMN_LOCALE, locale);
    values.put(RequestColumns.COLUMN_DATA, responseData);
    values.put(RequestColumns.COLUMN_REQUEST_TYPE, requestType);

    getDatabase().insert(TABLE_NAME, values);
  }

  public int deleteRequest(String url) {

    String selection = RequestColumns.COLUMN_URL + "=?";
    String[] selectionArgs = { url };
    return getDatabase().delete(TABLE_NAME, selection, selectionArgs);
  }

  public int deleteRequestsOfType(String type) {

    String selection = RequestColumns.COLUMN_REQUEST_TYPE + "=?";
    String[] selectionArgs = { type };
    return getDatabase().delete(TABLE_NAME, selection, selectionArgs);
  }

  public RequestMeta getRequest(String url) {

    String selection = RequestColumns.COLUMN_URL + "=?";
    String[] selectionArgs = { url };

    Cursor cursor = null;

    try {
      cursor = getDatabase().query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
      if (cursor != null) {
        if (cursor.moveToFirst()) {
          int id = cursor.getInt(cursor.getColumnIndex(RequestColumns.COLUMN_ID));
          String requestUrl = cursor.getString(cursor.getColumnIndex(RequestColumns.COLUMN_URL));
          String eTag = cursor.getString(cursor.getColumnIndex(RequestColumns.COLUMN_E_TAG));
          String locale = cursor.getString(cursor.getColumnIndex(RequestColumns.COLUMN_LOCALE));
          String data = cursor.getString(cursor.getColumnIndex(RequestColumns.COLUMN_DATA));
          String requestType = cursor.getString(cursor.getColumnIndex(RequestColumns.COLUMN_REQUEST_TYPE));
          long lastModified = Long.parseLong(cursor.getString(cursor
              .getColumnIndex(RequestColumns.COLUMN_MAX_AGE)));

          return new RequestMeta(id, requestUrl, eTag, lastModified, locale, data, requestType);
        }
      }
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }

    return null;
  }

  @Override
  public void upgradeTables(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getCreateTableSql() {
    return SQL_CREATE_TABLE;
  }

  @Override
  public void updateTables(SQLiteDatabase db) {
    // TODO Auto-generated method stub

  }
}

