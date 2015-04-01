package com.project.monica.snobsinenobilitate.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewStub;
import android.widget.Toolbar;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.bus.ScopedBus;

/**
 * Created by monica on 25/02/2015.
 */
public abstract class BaseActivity extends FragmentActivity {

  // TODO - IN PROGRESS: to make it more scalable, i am going to extend this class to avoid to insert the toolbar in every Activity
  // here i am inserting also common activity navigation and registration of the activity in the bus.

  private final ScopedBus scopedBus = new ScopedBus();

  protected ScopedBus getBus() {
    return scopedBus;
  }

  protected abstract int getLayoutResourceId();

  private Toolbar toolbar;

  @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    initialiseLayout();
    if (isToolbarShown()) {
      initialiseToolbar();
    }
  }

  public Toolbar getToolbar() {
    return toolbar;
  }

  private void initialiseLayout() {
    setContentView(R.layout.activity_base);

    ViewStub viewStub = (ViewStub) findViewById(R.id.stub);
    viewStub.setLayoutResource(getLayoutResourceId());
    viewStub.inflate();
  }

  private void initialiseToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getString(R.string.app_name));
    setActionBar(toolbar);
  }

  boolean isToolbarShown() {
    return true;
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
