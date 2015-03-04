package com.project.monica.snobsinenobilitate.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toolbar;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.fragments.ProductDetailFragment;

public class ProductDetailActivity extends FragmentActivity implements
    ProductDetailFragment.OnFragmentInteractionListener {
  public final static String PRODUCT_ID = "product:id";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getString(R.string.app_name));
    setActionBar(toolbar);

    getActionBar().setDisplayHomeAsUpEnabled(true);
    String productId = getIntent().getStringExtra(PRODUCT_ID);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_container, ProductDetailFragment.newInstance(productId))
        .commit();
  }

  @Override public void onFragmentInteraction(Uri uri) {

  }
}
