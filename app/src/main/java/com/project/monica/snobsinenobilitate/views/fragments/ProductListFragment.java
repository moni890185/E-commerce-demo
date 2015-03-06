package com.project.monica.snobsinenobilitate.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.project.monica.snobsinenobilitate.AutofitRecyclerView;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.bus.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.bus.events.ProductListContentEvent;
import com.project.monica.snobsinenobilitate.models.ApiModel;
import com.project.monica.snobsinenobilitate.models.pojo.Product;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.project.monica.snobsinenobilitate.views.adapters.ProductListAdapter;
import com.squareup.otto.Subscribe;
import java.util.List;

public class ProductListFragment extends BaseFragment
    implements ProductListAdapter.OnCustomItemClickListener {

  public interface OnProductListItemListener {
    public void onProductListItemClick(String productId);
  }

  private List<Product> mDataset;
  private String mProductCategory = "day-dresses";
  private AutofitRecyclerView mRecyclerView;
  private ProductListAdapter mProductListAdapter;
  private OnProductListItemListener mProductListItemListener;

  public static ProductListFragment newInstance() {
    ProductListFragment fragment = new ProductListFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  public ProductListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private void initDataset() {
    ApiModel.getInstance().getCategoryProducts(mProductCategory);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_product_list, container, false);
    initViews(v);
    initDataset();
    return v;
  }

  private void initViews(View v) {
    mRecyclerView = (AutofitRecyclerView) v.findViewById(R.id.recycler_view_product_list);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.setHasFixedSize(true);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mProductListItemListener = (OnProductListItemListener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mProductListItemListener = null;
  }

  private void initAdapter() {
      if (mProductListAdapter == null) {
        Logger.d("adapter is null");
        mProductListAdapter = new ProductListAdapter(getActivity(), mDataset, this);
      }
      mRecyclerView.setAdapter(mProductListAdapter);
  }

  @Override
  public void onItemClick(String productId) {
    if (mProductListItemListener != null) {
      mProductListItemListener.onProductListItemClick(productId);
    }
  }

  // Subscribers

  @Subscribe
  public void onCategoryProductsReceived(ProductListContentEvent event) {
    mDataset = event.getProductList().getProducts();
    initAdapter();
  }

  @Subscribe
  public void onNetworkErrorEvent(NetworkErrorEvent event) {
    // TODO implement error view.
  }
}
