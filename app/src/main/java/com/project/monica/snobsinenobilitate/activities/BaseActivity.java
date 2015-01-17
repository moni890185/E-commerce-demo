package com.project.monica.snobsinenobilitate.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.navigationdrawer.NavDrawerActivityConfiguration;
import com.project.monica.snobsinenobilitate.navigationdrawer.NavMenuLeaf;

import timber.log.Timber;

public abstract class BaseActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private android.support.v4.app.ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private NavDrawerActivityConfiguration navConf;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    protected abstract  void onNavItemSelected(NavDrawerActivityConfiguration.MENU id);

    protected abstract NavDrawerActivityConfiguration getNavDrawerConfiguration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navConf = getNavDrawerConfiguration();
        Timber.d("onCreate - Base activity");
        setContentView(navConf.getMainLayout());
        mDrawerLayout = (DrawerLayout) findViewById(navConf.getDrawerLayoutId());

        mDrawerList = (ListView) findViewById(navConf.getLeftDrawerId());
        mDrawerList.setAdapter(navConf.getBaseAdapter());
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(navConf.getDrawerShadow(), GravityCompat.START);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // setting toggle for drawer layout
        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                getDrawerIcon(),  /* nav drawer image to replace 'Up' caret */
                navConf.getDrawerOpenDesc(),
                navConf.getDrawerCloseDesc() /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // set Title
        mTitle = mDrawerTitle = getTitle();

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    public void selectItem(int position) {
        NavMenuLeaf selectedItem = navConf.getNavItems()[position];

        this.onNavItemSelected(selectedItem.getNavDrawerId());
        mDrawerList.setItemChecked(position, true);

//        if ( selectedItem.updateActionBarTitle()) {
//            setTitle(selectedItem.getNavDrawerLabel());
//        }

        if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
                this.mDrawerLayout.closeDrawer(this.mDrawerList);
            } else {
                this.mDrawerLayout.openDrawer(this.mDrawerList);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected int getDrawerIcon() {
        return R.drawable.ic_drawer;
    }

//    protected void onNavItemSelected(NavDrawerActivityConfiguration.MENU id) {
//        if (id == NavDrawerActivityConfiguration.MENU.COLLECTION) {
//            Intent intent = new Intent();
//            intent.setClass(this, ProductListActivity.class);
//            startActivity(intent);
//        } else if (id == NavDrawerActivityConfiguration.MENU.ABOUT_US) {
//            Intent intent = new Intent();
//            intent.setClass(this, AboutUsActivity.class);
//            startActivity(intent);
//        } else if (id == NavDrawerActivityConfiguration.MENU.CONTACT_US) {
//            Intent intent = new Intent();
//            intent.setClass(this, ContactUsActivity.class);
//            startActivity(intent);
//
//        } else if (id == NavDrawerActivityConfiguration.MENU.STORES) {
//            Intent intent = new Intent();
//            intent.setClass(this, StoreFinderActivity.class);
//            startActivity(intent);
//
//        } else if (id == NavDrawerActivityConfiguration.MENU.HOME) {
//            Intent intent = new Intent();
//            intent.setClass(this, HomeActivity.class);
//            startActivity(intent);
//        }
//    }
}