package com.project.monica.snobsinenobilitate.activities;

import android.support.v4.app.FragmentActivity;
import com.project.monica.snobsinenobilitate.otto.BusProvider;

/**
 * Created by monica on 25/02/2015.
 */
public abstract class BaseActivity extends FragmentActivity {

  @Override protected void onStart() {
    super.onStart();
    BusProvider.getInstance().register(this);
  }

  @Override protected void onStop() {
    super.onStop();
    BusProvider.getInstance().register(this);
  }
}
