package com.project.monica.snobsinenobilitate.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.project.monica.snobsinenobilitate.AutofitRecyclerView;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.adapters.ProductListAdapter;
import com.project.monica.snobsinenobilitate.events.NetworkErrorEvent;
import com.project.monica.snobsinenobilitate.events.ProductListContentEvent;
import com.project.monica.snobsinenobilitate.models.ApiModel;
import com.project.monica.snobsinenobilitate.models.pojo.collection.Product;
import com.squareup.otto.Subscribe;
import java.util.List;

public class ProductListFragment extends BaseFragment
    implements ProductListAdapter.OnCustomItemClickListener {

  public interface OnProductListItemListener {
    public void onProductListItemClick(String productId);
  }

  private static final String TITLE = "title";
  private int mTitle;
  private List<Product> mDataset;
  private String mProductCategory = "day-dresses";
  private AutofitRecyclerView mRecyclerView;
  private ProductListAdapter mProductListAdapter;
  private OnProductListItemListener mProductListItemListener;

  public static ProductListFragment newInstance(int title) {
    ProductListFragment fragment = new ProductListFragment();
    Bundle args = new Bundle();
    args.putInt(TITLE, title);
    fragment.setArguments(args);
    return fragment;
  }

  public ProductListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mTitle = getArguments().getInt(TITLE);
    }
    initDataset();
  }

  private void initDataset() {
    ApiModel.getInstance().getCategoryProducts(mProductCategory);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_product_list, container, false);
    mRecyclerView = (AutofitRecyclerView) v.findViewById(R.id.recycler_view_product_list);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.setHasFixedSize(true);
    return v;
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

  @Subscribe
  public void onCategoryProductsReceived(ProductListContentEvent event) {
    mDataset = event.getProductList().getProducts();
    initAdapter();
  }

  @Subscribe
  public void onNetworkErrorEvent(NetworkErrorEvent event) {
    // TODO implement error view.
  }

  private void initAdapter() {
    if (mProductListAdapter == null) {
      mProductListAdapter = new ProductListAdapter(getActivity(), mDataset, this);
      mRecyclerView.setAdapter(mProductListAdapter);
    }
    mProductListAdapter.notifyDataSetChanged();
  }

  @Override
  public void onItemClick(String productId) {
    if (mProductListItemListener != null) {
      mProductListItemListener.onProductListItemClick(productId);
    }
  }
}
