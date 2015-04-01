package com.project.monica.snobsinenobilitate.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.bus.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.bus.events.ProductDetailContentEvent;
import com.project.monica.snobsinenobilitate.models.ApiModel;
import com.project.monica.snobsinenobilitate.models.pojo.Color;
import com.project.monica.snobsinenobilitate.models.pojo.Product;
import com.project.monica.snobsinenobilitate.models.pojo.Size;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.project.monica.snobsinenobilitate.ui.activities.ProductDetailActivity;
import com.project.monica.snobsinenobilitate.ui.adapters.ColorsAdapter;
import com.project.monica.snobsinenobilitate.ui.adapters.SizeAdapter;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductDetailFragment extends BaseFragment implements View.OnClickListener {

  // todo use Butterknife and create custom view for color and size card.
  private ImageView mImageView;
  private TextView mPriceView;
  private TextView mTitleView;
  private TextView mDescription;
  private Product mProduct;
  private Button mSaveButton;
  private Button mBuyButton;
  private String mProductId;
  private LinearLayout mCardView;
  private RecyclerView mRecyclerColors;
  private RecyclerView mRecyclerSizes;

  private LinearLayoutManager mColorLinearLayoutManager;
  private LinearLayoutManager mSizeLinearLayoutManager;
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

  private void initViews(View v) {
    mImageView = (ImageView) v.findViewById(R.id.product_detail_img);
    mPriceView = (TextView) v.findViewById(R.id.product_detail_price);
    mTitleView = (TextView) v.findViewById(R.id.product_detail_title);
    mBuyButton = (Button) v.findViewById(R.id.product_detail_buy_button);
    mSaveButton = (Button) v.findViewById(R.id.product_detail_save_button);
    mDescription = (TextView) v.findViewById(R.id.product_detail_description);
    mCardView = (LinearLayout)v.findViewById(R.id.card_view_colors);
    mRecyclerColors = (RecyclerView) mCardView.findViewById(R.id.product_detail_container_colors);
    mRecyclerSizes = (RecyclerView) v.findViewById(R.id.product_detail_container_sizes);
    mBuyButton.setOnClickListener(this);
    mSaveButton.setOnClickListener(this);

    initLayoutManager();
  }

  private void initLayoutManager() {
     mColorLinearLayoutManager =
        new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
    mRecyclerColors.setLayoutManager(mColorLinearLayoutManager);

     mSizeLinearLayoutManager =
        new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
    mRecyclerSizes.setLayoutManager(mSizeLinearLayoutManager);
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
    mTitleView.setText(mProduct.getName());
    mDescription.setText(Html.fromHtml(mProduct.getDescription()));

    initColorCard();
    initSizeCard();

    String imageUrl = mProduct.getImage().getSizes().getOriginal().getUrl();
    Picasso.with(getActivity()).load(imageUrl).into(mImageView);
  }

  private void initColorCard() {
    List<Color> colors = mProduct.getColors();
    mRecyclerColors.setHasFixedSize(true);
    Logger.d("colors: "+ colors.size());
    mRecyclerColors.setAdapter(new ColorsAdapter(getActivity(), colors));
    mRecyclerColors.setItemAnimator(new DefaultItemAnimator());
  }

  private void initSizeCard() {
    List<Size> size = mProduct.getSizes();
    mRecyclerSizes.setHasFixedSize(true);
    mRecyclerSizes.setAdapter(new SizeAdapter(getActivity(), size));
    mRecyclerSizes.setItemAnimator(new DefaultItemAnimator());
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
}
