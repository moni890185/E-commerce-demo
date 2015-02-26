package com.project.monica.snobsinenobilitate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.project.monica.snobsinenobilitate.utils.DeveloperConstants;
import java.util.ArrayList;

public class SSNDatabase {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "SSNDatabase.db";

  private SSNDatabaseHelper dbHelper;

  public void setHelpers(Context context, DatabaseHelper[] helpers) {
    dbHelper = new SSNDatabaseHelper(this, context, helpers);
  }

  SSNDatabaseHelper getSSNDatabaseHelper() {
    return dbHelper;
  }

  public void execSql(String sql) {
    dbHelper.getWritableDatabase().execSQL(sql);
  }

  public void insert(String tableName, ArrayList<ContentValues> values) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    try {
      DatabaseUtils.InsertHelper helper = new InsertHelper(db, tableName);
      final int totalInserts = values.size();

      db.beginTransaction();

      for (int i = 0; i < totalInserts; i++) {
        helper.insert(values.get(i));
      }

      db.setTransactionSuccessful();
    } catch (Exception e) {
      if (DeveloperConstants.LOGGING_ENABLED) {
        e.printStackTrace();
      }
    }

    db.endTransaction();
  }

  public void insert(String tableName, ContentValues values) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    try {
      DatabaseUtils.InsertHelper helper = new InsertHelper(db, tableName);
      helper.insert(values);
    } catch (Exception e) {
      if (DeveloperConstants.LOGGING_ENABLED) {
        e.printStackTrace();
      }
    }
  }

  public Cursor query(String table, String[] columns, String selection, String[] selectionArgs,
      String groupBy,
      String having, String orderBy) {
    SQLiteDatabase db = dbHelper.getReadableDatabase();

    try {
      return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    } catch (IllegalArgumentException iae) {
      if (DeveloperConstants.LOGGING_ENABLED) {
        iae.printStackTrace();
      }
      return null;
    }
  }

  public int delete(String table, String whereClause, String[] whereArgs) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    return db.delete(table, whereClause, whereArgs);
  }

  public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    return db.update(table, values, whereClause, whereArgs);
  }

  public int clearDataFromTable(String table) {
    return delete(table, null, null);
  }

  public void dropTable(String tableName) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    db.execSQL("DROP TABLE " + tableName);
  }

  static class SSNDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = SSNDatabaseHelper.class.getSimpleName();
    com.project.monica.snobsinenobilitate.database.SSNDatabase SSNDatabase;
    DatabaseHelper[] helpers;

    public SSNDatabaseHelper(com.project.monica.snobsinenobilitate.database.SSNDatabase SSNDatabase, Context context, DatabaseHelper[] helpers) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
      this.SSNDatabase = SSNDatabase;
      this.helpers = helpers;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      try {
        if (helpers != null) {
          final int totalTables = helpers.length;
          for (int i = 0; i < totalTables; i++) {
            db.execSQL(helpers[i].getCreateTableSql());
            helpers[i].updateTables(db);
          }
        }
      } catch (Exception e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      try {
        if (helpers != null) {
          final int totalTables = helpers.length;

          for (int i = 0; i < totalTables; i++) {
            helpers[i].upgradeTables(db, oldVersion, newVersion);
          }

          onCreate(db);
        }
      } catch (Exception e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      }
    }
  }
}
