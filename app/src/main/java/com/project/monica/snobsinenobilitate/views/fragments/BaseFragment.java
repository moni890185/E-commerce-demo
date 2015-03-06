package com.project.monica.snobsinenobilitate.views.fragments;

import android.support.v4.app.Fragment;
import com.project.monica.snobsinenobilitate.bus.ScopedBus;

/**
 * Created by monica on 01/03/2015.
 */
public class BaseFragment extends Fragment {
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
