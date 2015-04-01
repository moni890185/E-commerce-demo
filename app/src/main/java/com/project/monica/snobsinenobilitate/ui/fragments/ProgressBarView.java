package com.project.monica.snobsinenobilitate.ui.fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.project.monica.snobsinenobilitate.R;

/**
 * Custom Progress Bar.
 */
public class ProgressBarView extends ImageView {

  private Animation mRotateAnimation;

  public ProgressBarView(Context context) {
    super(context);
    initialise(context);
  }

  public ProgressBarView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialise(context);
  }

  public ProgressBarView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialise(context);
  }

  private void initialise(Context context) {
    setImageResource(R.drawable.ic_progress_bar);
    setVisibility(View.INVISIBLE);
    mRotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
  }

  /**
   * Start the rotating animation of the progress bar. The call to this method makes the view
   * visible.
   */
  public void start() {
    setVisibility(View.VISIBLE);
    startAnimation(mRotateAnimation);
  }

  /**
   * Stop the rotating animation of the progress bar. The call to this method makes the view
   * invisible.
   */
  public void stop() {
    setVisibility(View.INVISIBLE);
    clearAnimation();
  }
}