package com.project.monica.openreplyproject.Utils;

import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by monica on 05/03/2015.
 */
public class GUIUtils {
  private static final int SCALE_FACTOR = 50;

  public static void hideViewByScale(View view) {

    ViewPropertyAnimator propertyAnimator = view.animate().setStartDelay(SCALE_FACTOR)
        .scaleY(0);
    propertyAnimator.start();
  }

  public static void showViewByScale(View view) {

    ViewPropertyAnimator propertyAnimator = view.animate().setStartDelay(SCALE_FACTOR)
        .scaleY(1);
    propertyAnimator.start();
  }
}
