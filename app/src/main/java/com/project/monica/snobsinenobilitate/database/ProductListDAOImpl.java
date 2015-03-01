package com.project.monica.snobsinenobilitate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.project.monica.snobsinenobilitate.database.dao.ProductListDAO;
import com.project.monica.snobsinenobilitate.models.DBModel;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import com.project.monica.snobsinenobilitate.utils.Logger;
import java.util.List;

/**
 * Created by monica on 26/02/2015.
 */
public class ProductListDAOImpl extends DatabaseHelper implements ProductListDAO {

  public ProductListDAOImpl(SSNDatabase database) {
    super(database);
  }

  private static final String TABLE_NAME = "product_list";

  public static class ProductListColumns extends DatabaseHelperColumns {
    private static final String COLUMN_CATEGORY_CODE = "category_code";
    private static final String COLUMN_CATEGORY_NAME = "category_code";
    private static final String COLUMN_PRODUCT_LIST = "product_list";
  }

  public static final String SQL_CREATE_TABLE = "CREATE TABLE "
      + TABLE_NAME
      + " ("
      + //
      ProductListColumns.COLUMN_ID
      + ProductListColumns.TYPE_INTEGER
      + " PRIMARY KEY AUTOINCREMENT"
      + ProductListColumns.COMMA_SEP
      + //

      ProductListColumns.COLUMN_CATEGORY_CODE
      + ProductListColumns.TYPE_TEXT
      + ProductListColumns.COMMA_SEP
      + //
      ProductListColumns.COLUMN_CATEGORY_NAME
      + ProductListColumns.TYPE_TEXT
      + ProductListColumns.COMMA_SEP
      + //

      ProductListColumns.COLUMN_PRODUCT_LIST
      + ProductListColumns.TYPE_TEXT
      +
      " )";

  public ProductList getProductList(Context context, String categoryCode, String categoryName) {
    String selection = ProductListColumns.COLUMN_CATEGORY_CODE + " = ?";
    String[] selectionArgs = {categoryCode};
    Cursor listCursor = null;
    Logger.d("getProductList : selection is "+ selection + " selectionArgs: "+ selectionArgs);

    try {
      listCursor =
          getDatabase().query(TABLE_NAME, null, selection, selectionArgs, null, null, null);

      if (listCursor != null && listCursor.moveToFirst()) {
        String productList = listCursor.getString(listCursor
            .getColumnIndex(ProductListColumns.COLUMN_PRODUCT_LIST));

        String[] productIds = productList.split(",");
        List<Product> products = getProducts(context, productIds, null);

        return new ProductList(categoryCode, categoryName, products);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (listCursor != null) {
        listCursor.close();
      }
    }

    return null;
  }

  // todo
  public List<Product> getProducts(Context context, String[] productIds, String sort) {

    return DBModel.getInstance(context).getProductDAO().getProducts(context, productIds);
  }

  public void addProductList(Context context, ProductList productList) {
    ProductList oldList = null;
    String deleteKey = null;
    String categoryName = null;

      deleteKey = productList.getMetadata().getCategory().getCode();
    categoryName = productList.getMetadata().getCategory().getName();

    Logger.d("productList cat code is "+ productList.getMetadata().getCategory().getCode());

      // Passing null as we don't care about the order they are added, as
      // the should always be fetched with an order.
      oldList = getProductList(context, deleteKey, categoryName);


    ContentValues values = toContentValues(productList, oldList);

    // Delete the old records from disk as they are about to be updated and
    // we don't want multiple copies.
    clearProductList(deleteKey);

    // Write content list
    getDatabase().insert(TABLE_NAME, values);

    // Add child products
    addProducts(context, productList.getProducts());
  }

  public int clearProductList(String... deleteKeys) {
    StringBuilder whereClauseBuider = new StringBuilder();
    String[] whereArgs = new String[deleteKeys.length];

    for (int i = 0; i < deleteKeys.length; i++) {
      whereClauseBuider.append(ProductListColumns.COLUMN_CATEGORY_CODE);
      whereClauseBuider.append(" LIKE ? OR ");
      whereArgs[i] = deleteKeys[i];
    }

    // Remove last " OR "
    int startIndex = whereClauseBuider.lastIndexOf(" OR ");
    whereClauseBuider.delete(startIndex, startIndex + " OR ".length());

    return getDatabase().delete(TABLE_NAME, whereClauseBuider.toString(), whereArgs);
  }

  public void clearProductListTable() {
    getDatabase().clearDataFromTable(TABLE_NAME);
  }

  public void addProducts(Context context, List<Product> products) {
     DBModel.getInstance(context).getProductDAO().addProducts(context, products);
  }

  private ContentValues toContentValues(ProductList productList,
      ProductList oldList) {
    ContentValues values = new ContentValues();

    String code =  productList.getMetadata().getCategory().getCode();
    String name = productList.getMetadata().getCategory().getName();
    values.put(ProductListColumns.COLUMN_CATEGORY_CODE, code);
    values.put(ProductListColumns.COLUMN_CATEGORY_NAME, name);

    String idList = null;

    if (oldList == null) {
      idList = productList.productArrayToCommaList();
    } else {
      int totalOldItems = 0;
      int totalNewItems = 0;
      if (oldList.getProducts() != null) {
        totalOldItems = oldList.getProducts().size();
      }
      if (productList.getProducts() != null) {
        totalNewItems = productList.getProducts().size();
      }
      final int totalItems = totalOldItems + totalNewItems;

      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < totalItems; i++) {
        if (i < totalOldItems) {
          sb.append(oldList.getProducts().get(i).getCode());
          sb.append(',');
        } else {
          sb.append(productList.getProducts().get(i - totalOldItems).getCode());
          sb.append(',');
        }
      }

      int lastCommaIndex = sb.lastIndexOf(",");
      if (lastCommaIndex > -1) {
        sb.deleteCharAt(lastCommaIndex);
      }

      idList = sb.toString();
    }

    values.put(ProductListColumns.COLUMN_PRODUCT_LIST, idList);

    return values;
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


