package com.project.monica.snobsinenobilitate.activities;

import android.support.v4.app.FragmentActivity;
import com.project.monica.snobsinenobilitate.otto.ScopedBus;

/**
 * Created by monica on 25/02/2015.
 */
public abstract class BaseActivity extends FragmentActivity {

  private final ScopedBus scopedBus = new ScopedBus();

  protected ScopedBus getBus() {
    return scopedBus;
  }

  @Override public void onPause() {
    super.onPause();
    scopedBus.paused();
  }

  @Override public void onResume() {
    super.onResume();
    scopedBus.resumed();
  }

  @Override
  public void onStart() {
    scopedBus.register(this);
    super.onStart();
  }

  @Override
  public void onStop() {
    scopedBus.unregister(this);
    super.onStop();
  }
}
