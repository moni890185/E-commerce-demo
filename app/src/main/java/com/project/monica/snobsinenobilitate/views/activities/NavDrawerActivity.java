package com.project.monica.snobsinenobilitate.views.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.views.fragments.ContactUsFragment;
import com.project.monica.snobsinenobilitate.views.fragments.HomeFragment;
import com.project.monica.snobsinenobilitate.views.fragments.NavigationDrawerFragment;
import com.project.monica.snobsinenobilitate.views.fragments.ProductListFragment;
import com.project.monica.snobsinenobilitate.views.fragments.StoreFinderFragment;

public class NavDrawerActivity extends FragmentActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks,
    ProductListFragment.OnProductListItemListener {

  public static final int COLLECTION = 1;
  public static final int HOME = 0;
  public static final int STORE_FINDER = 2;
  public static final int CONTACT_US = 3;
  private NavigationDrawerFragment mNavigationDrawerFragment;
  private CharSequence mTitle;

  //@Override protected int getLayoutResourceId() {
  //  return R.layout.activity_nav_drawer;
  //}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nav_drawer);

    // setting the action bar.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getString(R.string.app_name));
    setActionBar(toolbar);

    mNavigationDrawerFragment = (NavigationDrawerFragment)
        getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

    mTitle = getTitle();

    // Set up the drawer.
    mNavigationDrawerFragment.setUp(
        R.id.navigation_drawer,
        (DrawerLayout) findViewById(R.id.drawer_layout));
  }

  @Override
  public void onNavigationDrawerItemSelected(int position) {
    // update the main content by replacing fragments
    switch (position) {
      case HOME:
        showFragment(HomeFragment.newInstance());
        break;
      case COLLECTION:
        showFragment(ProductListFragment.newInstance());
        break;
      case STORE_FINDER:
        showFragment(StoreFinderFragment.newInstance());
        break;

      case CONTACT_US:
        showFragment(ContactUsFragment.newInstance());
        break;
    }
  }

  private void showFragment(Fragment fragment) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.container, fragment)
        .addToBackStack(null)
        .commit();
  }

  public void restoreActionBar() {
    ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    actionBar.setDisplayShowTitleEnabled(true);
    actionBar.setTitle(mTitle);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    if (!mNavigationDrawerFragment.isDrawerOpen()) {
      // Only show items in the action bar relevant to this screen
      // if the drawer is not showing. Otherwise, let the drawer
      // decide what to show in the action bar.
      getMenuInflater().inflate(R.menu.nav_drawer, menu);
      restoreActionBar();
      return true;
    }
    return super.onCreateOptionsMenu(menu);
  }

  /**
   * Implementing {@link ProductListFragment} listener related to the user item click when he
   * navigates in the product list.
   */
  @Override
  public void onProductListItemClick(String productId) {

    Intent intent = new Intent(this, ProductDetailActivity.class);
    intent.putExtra(ProductDetailActivity.PRODUCT_ID, productId);
    startActivity(intent);
  }

  @Override public void onBackPressed() {
    int entryCount = getSupportFragmentManager().getBackStackEntryCount();
    boolean isComingFromFragment = entryCount > 1;

    if (mNavigationDrawerFragment.isDrawerOpen()) {
      mNavigationDrawerFragment.closeMenu();
    } else if (isComingFromFragment) {
      super.onBackPressed();
    } else {
      finish();
    }
  }
}
