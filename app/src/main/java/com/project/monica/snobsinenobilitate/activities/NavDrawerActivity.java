package com.project.monica.snobsinenobilitate.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.fragments.AboutFragment;
import com.project.monica.snobsinenobilitate.fragments.ContactUsFragment;
import com.project.monica.snobsinenobilitate.fragments.HomeFragment;
import com.project.monica.snobsinenobilitate.fragments.NavigationDrawerFragment;
import com.project.monica.snobsinenobilitate.fragments.ProductListFragment;
import com.project.monica.snobsinenobilitate.fragments.StoreFinderFragment;
import com.project.monica.snobsinenobilitate.model.Product;

public class NavDrawerActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ProductListFragment.OnFragmentInteractionListener {

    public static final String ID_IMAGE = "ID_IMAGE";
    public static final String PRICE = "PRICE";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

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

        //   MENU {
        //          HOME,
        //          COLLECTION,
        //          STORES,
        //          CONTACT_US,
        //          ABOUT
        //        }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position)
        {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance(position + 1))
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ProductListFragment.newInstance(position + 1))
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, StoreFinderFragment.newInstance(position + 1))
                        .commit();
                break;

            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ContactUsFragment.newInstance(position + 1))
                        .commit();
                break;

            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AboutFragment.newInstance(position + 1))
                        .commit();
                break;

        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;

            case 2:
                mTitle = getString(R.string.title_section2);
                break;

            case 3:
                mTitle = getString(R.string.title_section3);
                break;

            case 4:
                mTitle = getString(R.string.title_section4);
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
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
    public void onFragmentInteraction(Product product) {
        // ProductListFragment - onItemClicked
        //launching ProductDetailActivity
        Intent i = new Intent();
        i.putExtra(ID_IMAGE,product.getImageDrawable());
        i.putExtra(PRICE,product.getPrice());
        i.setClass(this, ProductDetailActivity.class);
        startActivity(i);

    }

}
