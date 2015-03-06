package com.project.monica.snobsinenobilitate.utils;

/**
 * Created by monica on 04/03/2015.
 */
public class Utils {

  public static final String REGULAR = "Regular";

  public static String sanitizeSize(String sizeName) {
    String size = sizeName;
    String[] inputSplit = sizeName.split(REGULAR);
    if (inputSplit.length > 1) {
      size = inputSplit[1].trim();
      Logger.d(size);
    }
    return size;
  }
}
