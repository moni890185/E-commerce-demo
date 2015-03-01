package com.project.monica.snobsinenobilitate.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {
  public final static String PRODUCT_ID = "product:id";

  private ImageView mImage;
  private TextView mPrice;
  private TextView mTitle;
  private Product mProduct;
  private Button mSaveButton;
  private Button mBuyButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getString(R.string.app_name));
    setActionBar(toolbar);
    getActionBar().setDisplayHomeAsUpEnabled(true);

    initViews();
    initData();
    insertContent();
  }

  private void initViews() {
    mImage = (ImageView) findViewById(R.id.product_detail_img);
    mPrice = (TextView) findViewById(R.id.product_detail_price);
    mTitle = (TextView) findViewById(R.id.product_detail_title);
    mBuyButton = (Button) findViewById(R.id.product_detail_buy_button);
    mSaveButton = (Button) findViewById(R.id.product_detail_save_button);

    mBuyButton.setOnClickListener(this);
    mSaveButton.setOnClickListener(this);
  }

  private void initData() {
    String productId = getIntent().getStringExtra(PRODUCT_ID);
    //mProduct = Product.getProduct(productId);
  }

  private void insertContent() {
    //mPrice.setText(
    //    TextUtils.concat(getResources().getString(R.string.price_currency), mProduct.getPrice()));
    //// the small image
    //mImage.setImageResource(mProduct.getImageId());
    //
    //mTitle.setText(mProduct.getTitle());
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.product_detail_buy_button:
        onBuyButtonPressed();
        break;

      case R.id.product_detail_save_button:
        onSaveButtonPressed();
        break;
    }
  }

  private void onSaveButtonPressed() {
    Toast.makeText(this, "Save button pressed", Toast.LENGTH_SHORT).show();
    //TODO
  }

  private void onBuyButtonPressed() {
    Toast.makeText(this, "Buy button pressed", Toast.LENGTH_SHORT).show();
    //TODO
  }
}
