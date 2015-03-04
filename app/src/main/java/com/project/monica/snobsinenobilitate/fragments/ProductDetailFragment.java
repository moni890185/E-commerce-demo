package com.project.monica.snobsinenobilitate.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.activities.ProductDetailActivity;
import com.project.monica.snobsinenobilitate.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.events.ProductDetailContentEvent;
import com.project.monica.snobsinenobilitate.models.ApiModel;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends BaseFragment implements View.OnClickListener {

  private ImageView mImageView;
  private TextView mPriceView;
  private TextView mTitleView;
  private TextView mDescription;
  private Product mProduct;
  private Button mSaveButton;
  private Button mBuyButton;
  private String mProductId;

  private OnFragmentInteractionListener mListener;

  public static ProductDetailFragment newInstance(String productID) {
    ProductDetailFragment fragment = new ProductDetailFragment();
    Bundle args = new Bundle();
    args.putString(ProductDetailActivity.PRODUCT_ID, productID);
    fragment.setArguments(args);
    return fragment;
  }

  public ProductDetailFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mProductId = getArguments().getString(ProductDetailActivity.PRODUCT_ID);
    }
    ApiModel.getInstance().getProductDetail(mProductId);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.fragment_product_detail, container, false);
    initViews(v);
    return v;
  }

  @Subscribe
  public void onProductDetailContentRetrieved(ProductDetailContentEvent event) {
    Product productReceived = event.getProduct();
    if (productReceived.getCode().equals(mProductId)) {
      mProduct = productReceived;
      insertContent();
    }
  }

  @Subscribe
  public void onNetworkErrorEvent(NetworkErrorEvent event) {
    //show error view
  }

  private void initViews(View v) {
    mImageView = (ImageView) v.findViewById(R.id.product_detail_img);
    mPriceView = (TextView) v.findViewById(R.id.product_detail_price);
    mTitleView = (TextView) v.findViewById(R.id.product_detail_title);
    mBuyButton = (Button) v.findViewById(R.id.product_detail_buy_button);
    mSaveButton = (Button) v.findViewById(R.id.product_detail_save_button);


    mBuyButton.setOnClickListener(this);
    mSaveButton.setOnClickListener(this);
  }

  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (OnFragmentInteractionListener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  private void insertContent() {
    mPriceView.setText(mProduct.getPriceLabel());
    // the small image
    String imageUrl = mProduct.getImage().getSizes().getOriginal().getUrl();
    Logger.d("img url: " + imageUrl);

    Picasso.with(getActivity()).load(imageUrl).into(mImageView);

    mTitleView.setText(mProduct.getName());
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
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

    //TODO
  }

  private void onBuyButtonPressed() {

    //TODO
  }

  public interface OnFragmentInteractionListener {
    public void onFragmentInteraction(Uri uri);
  }
}
