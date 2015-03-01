package com.project.monica.snobsinenobilitate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.project.monica.snobsinenobilitate.database.dao.ProductDAO;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Category;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Color;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Image;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Size;
import com.project.monica.snobsinenobilitate.network.JacksonConverter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monica on 26/02/2015.
 */
public class ProductDAOImpl extends DatabaseHelper implements ProductDAO {

  private static final String TABLE_NAME = "products";

  public static class ProductColumns extends DatabaseHelperColumns {
    private static final String COLUMN_CODE = "code";

    public static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_CURRENCY = "currency";

    private static final String COLUMN_PRICE = "price";

    private static final String COLUMN_PRICE_LABEL = "priceLabel";

    private static final String COLUMN_IN_STOCK = "inStock";

    private static final String COLUMN_LOCALE = "locale";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CLICK_URL = "clickUrl";

    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_COLORS = "colors";
    private static final String COLUMN_SIZES = "sizes";
    private static final String COLUMN_CATEGORIES = "categories";

    private static final String COLUMN_SEE_MORE_LABEL = "seeMoreLabel";
    private static final String COLUMN_SEE_MORE_URL = "seeMoreUrl";
    private static final String COLUMN_EXTRACT_DATE = "extractDate";

    private static final String COLUMN_FAVOURITE_COUNT = "favoriteCount";
    private static final String COLUMN_SALE_PRICE = "salePrice";

    private static final String COLUMN_SALE_PRICE_LABEL = "salePriceLabel";
  }

  public static final String SQL_CREATE_TABLE = "CREATE TABLE "
      + TABLE_NAME
      + " ("
      +
      //
      ProductColumns.COLUMN_ID
      + ProductColumns.TYPE_INTEGER
      + " PRIMARY KEY AUTOINCREMENT"
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_CODE
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_NAME
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_TYPE
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_CURRENCY
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_PRICE
      + ProductColumns.TYPE_INTEGER
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_PRICE_LABEL
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_IN_STOCK
      + ProductColumns.TYPE_BOOLEAN
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_LOCALE
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_DESCRIPTION
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_CLICK_URL
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_IMAGE
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_COLORS
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_SIZES
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_CATEGORIES
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_SEE_MORE_LABEL
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_SEE_MORE_URL
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_EXTRACT_DATE
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_FAVOURITE_COUNT
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //
      ProductColumns.COLUMN_SALE_PRICE
      + ProductColumns.TYPE_TEXT
      + ProductColumns.COMMA_SEP
      +
      //

      ProductColumns.COLUMN_SALE_PRICE_LABEL
      + ProductColumns.TYPE_TEXT
      +
      //

      " )";

  public ProductDAOImpl(SSNDatabase database) {
    super(database);
  }


  public Product getProduct(Context context, String productCode) {
    String selection = ProductColumns.COLUMN_CODE + " = ?";
    String[] selectionArgs = {productCode};

    Cursor cursor = null;
    try {
      cursor = getDatabase().query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
      if (cursor.getCount() > 0 && cursor.moveToFirst()) {
        Product product = getProduct(context, cursor);
        return product;
      }
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }

    return null;
  }

  public List<Product> getProducts(Context context, String[] productCodes) {
    Cursor cursor = null;
    String selection = null;
    String[] selectionArgs = null;
    Product[] products = null;
    // tmp no sorting implemented
    String orderBy = null;

    if (productCodes != null) {
      selectionArgs = productCodes;
      // if orderBy == NULL means query has to give us as output the items
      // ordered in the same order of the array (input).

      orderBy = manageOrderByOrderArray(productCodes);
      selection = manageRangeInArray(productCodes.length);
    }

    try {
      cursor = getDatabase().query(TABLE_NAME, null, selection, selectionArgs, null, null, orderBy);

      if (cursor != null && cursor.moveToFirst()) {
        products = new Product[cursor.getCount()];

        do {
          int pos = cursor.getPosition();
          products[pos] = getProduct(context, cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }

    return Arrays.asList(products);
  }

  /*
   * building query as: SELECT * FROM "products" WHERE ("code" IN (1,5,8,4))
   * ORDER BY id=1 ASC, ID=5 ASC, id=8 ASC, ID=4 ASC
   */
  private String manageRangeInArray(int productCodeslenght) {
    return "(" + ProductColumns.COLUMN_CODE + " IN (" + makePlaceholdersForInClause(
        productCodeslenght) + "))";
  }

  private String makePlaceholdersForInClause(int len) {
    if (len < 1) {
      throw new RuntimeException("No placeholders");
    } else {
      StringBuilder sb = new StringBuilder(len * 2 - 1);
      sb.append("?");
      for (int i = 1; i < len; i++) {
        sb.append(ProductColumns.COMMA_SEP + "?");
      }
      return sb.toString();
    }
  }

  private String manageOrderByOrderArray(String[] productCodes) {
    String orderString = "";
    for (int i = 0; i < productCodes.length; i++) {
      orderString += ProductColumns.COLUMN_CODE + "=" + "'" + productCodes[i] + "'" + " ASC";
      if (i != productCodes.length - 1) {
        orderString += ProductColumns.COMMA_SEP;
      }
    }
    return orderString;
  }
  public Product getProduct(Context context, Cursor cursor) {
    String code = cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_CODE));
    String name = cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_NAME));
    String type = cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_TYPE));
    String currency = cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_CURRENCY));
    String priceLabel =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_SALE_PRICE_LABEL));

    Integer price =
        cursor.getInt(cursor.getColumnIndex(ProductColumns.COLUMN_PRICE));
    boolean inStock =
        (cursor.getInt(cursor.getColumnIndex(ProductColumns.COLUMN_IN_STOCK)) == 1);
    String locale =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_LOCALE));
    String description =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_DESCRIPTION));
    String clickUrl = cursor.getString(cursor
        .getColumnIndex(ProductColumns.COLUMN_CLICK_URL));

    String seeMoreLabel = cursor.getString(cursor
        .getColumnIndex(ProductColumns.COLUMN_SEE_MORE_LABEL));
    String seeMoreUrl =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_SEE_MORE_URL));
    String extractDate =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_EXTRACT_DATE));
    Integer favoriteCount = cursor.getInt(cursor
        .getColumnIndex(ProductColumns.COLUMN_FAVOURITE_COUNT));

    Double salePrice =
        cursor.getDouble(cursor.getColumnIndex(ProductColumns.COLUMN_SALE_PRICE));

    String salePriceLabel =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_SALE_PRICE_LABEL));


    String imageJson = cursor.getString(cursor
        .getColumnIndex(ProductColumns.COLUMN_IMAGE));
    Image image = JacksonConverter.getItem(imageJson,Image.class);


    String colorsJson = cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_COLORS));
    Color[] colors = JacksonConverter.getItem(colorsJson,Color[].class);

    String sizesJson =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_SIZES));
    Size[] sizes = JacksonConverter.getItem(sizesJson,Size[].class);

    String categoriesJson =
        cursor.getString(cursor.getColumnIndex(ProductColumns.COLUMN_CATEGORIES));
    Category[] categories = JacksonConverter.getItem(categoriesJson,Category[].class);

    Product product =
        new Product(code, name, type, currency, price,
            priceLabel, inStock, locale, description,
            clickUrl, image,
            Arrays.asList(colors),
            Arrays.asList(sizes),
            Arrays.asList(categories), seeMoreLabel, seeMoreUrl,
            extractDate, favoriteCount, salePrice, salePriceLabel);

    return product;
  }

  private String createGetProductsSelectionString(int totalIds) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < totalIds; i++) {
      sb.append(ProductColumns.COLUMN_CODE + " = ? OR ");
    }

    // Remove the last " OR"
    sb.delete(sb.length() - 3, sb.length() - 1);
    return sb.toString();
  }

  public void clearProducts() {
    getDatabase().clearDataFromTable(TABLE_NAME);
  }

  public void addProducts(Context context, List<Product> products) {
    for (Product product : products) {
      if (product != null) {
        ContentValues values = toContentValues(context, product);

        // Try to update the product details if the product exists
        int rowsUpdated =
            getDatabase().update(TABLE_NAME, values, ProductColumns.COLUMN_CODE + " = ?",
                new String[] {product.getCode()});

        // If no products were updated, insert data
        if (rowsUpdated < 1) {
          getDatabase().insert(TABLE_NAME, values);
        }
      }
    }
  }


  private ContentValues toContentValues(Context context, Product product) {
    ContentValues values = new ContentValues();

    values.put(ProductColumns.COLUMN_CODE, product.getCode());
    values.put(ProductColumns.COLUMN_NAME, product.getName());
    values.put(ProductColumns.COLUMN_TYPE, product.getType());
    values.put(ProductColumns.COLUMN_PRICE, product.getPrice());
    values.put(ProductColumns.COLUMN_PRICE_LABEL, product.getPriceLabel());
    values.put(ProductColumns.COLUMN_IN_STOCK, (product.getInStock() ? 1 : 0));

    values.put(ProductColumns.COLUMN_CURRENCY, product.getCurrency());
    values.put(ProductColumns.COLUMN_SALE_PRICE_LABEL, product.getSalePriceLabel());
    values.put(ProductColumns.COLUMN_LOCALE,
        product.getLocale());
    values.put(ProductColumns.COLUMN_DESCRIPTION, product.getDescription());
    values.put(ProductColumns.COLUMN_CLICK_URL, product.getClickUrl());

    if(product.getImage()!=null)
    {
      String imageJson = JacksonConverter.toJson(product.getImage());
      values.put(ProductColumns.COLUMN_IMAGE,imageJson);
    }
    if(product.getSizes()!=null) {
      String sizesJson = JacksonConverter.toJson(product.getSizes());
      values.put(ProductColumns.COLUMN_SIZES, sizesJson);
    }
    if(product.getColors()!=null) {
      String colorsJson = JacksonConverter.toJson(product.getColors());
      values.put(ProductColumns.COLUMN_COLORS, colorsJson);
    }

    if(product.getCategories()!=null) {
      String categoriesJson = JacksonConverter.toJson(product.getCategories());
      values.put(ProductColumns.COLUMN_CATEGORIES, categoriesJson);
    }

    values.put(ProductColumns.COLUMN_SEE_MORE_LABEL, product.getSeeMoreLabel());
    values.put(ProductColumns.COLUMN_SEE_MORE_URL, product.getSeeMoreUrl());
    values.put(ProductColumns.COLUMN_EXTRACT_DATE, product.getExtractDate());
    values.put(ProductColumns.COLUMN_FAVOURITE_COUNT, product.getFavoriteCount());

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
