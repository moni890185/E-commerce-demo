package com.project.monica.snobsinenobilitate.activities;

import android.content.Intent;
import android.os.Bundle;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.adapter.MenuAdapter;
import com.project.monica.snobsinenobilitate.navigationdrawer.NavDrawerActivityConfiguration;
import com.project.monica.snobsinenobilitate.navigationdrawer.NavMenuLeaf;

import static com.project.monica.snobsinenobilitate.navigationdrawer.NavDrawerActivityConfiguration.MENU;

public class HomeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        NavMenuLeaf[] menu;
        menu = new NavMenuLeaf[]{
                NavMenuLeaf.create(MENU.HOME, "Home"),
                NavMenuLeaf.create(MENU.COLLECTION, "Collection"),
                NavMenuLeaf.create(MENU.STORES, "Stores"),
                NavMenuLeaf.create(MENU.CONTACT_US, "Contact us"),
                NavMenuLeaf.create(MENU.ABOUT_US, "About")};

        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_home);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(
                new MenuAdapter(this, R.layout.navdrawer_item, new String[]{"text"}));
        return navDrawerActivityConfiguration;
    }

    protected void onNavItemSelected(MENU id) {
        if (id == NavDrawerActivityConfiguration.MENU.COLLECTION) {
            Intent intent = new Intent();
            intent.setClass(this, ProductListActivity.class);
            startActivity(intent);
        } else if (id == NavDrawerActivityConfiguration.MENU.ABOUT_US) {
            Intent intent = new Intent();
            intent.setClass(this, AboutUsActivity.class);
            startActivity(intent);
        } else if (id == NavDrawerActivityConfiguration.MENU.CONTACT_US) {
            Intent intent = new Intent();
            intent.setClass(this, ContactUsActivity.class);
            startActivity(intent);

        } else if (id == NavDrawerActivityConfiguration.MENU.STORES) {
            Intent intent = new Intent();
            intent.setClass(this, StoreFinderActivity.class);
            startActivity(intent);

        } else if (id == NavDrawerActivityConfiguration.MENU.HOME) {

        }
    }
}
