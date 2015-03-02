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
import com.project.monica.snobsinenobilitate.models.pojo.collection.ProductList;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.squareup.otto.Subscribe;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

///**
// * Activities that contain this fragment must implement the
// * {@link ProductListFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// */
public class ProductListFragment extends BaseFragment
    implements ProductListAdapter.OnCustomItemClickListener {

  public interface OnProductListItemListener {
    public void onProductListItemClick(String productId);
  }

  private static final String TITLE = "title";

  // tmp Category
  private String mProductCategory = "day-dresses";

  private AutofitRecyclerView mRecyclerView;

  // Adapter
  private ProductListAdapter mProductListAdapter;

  /* Dataset with the images to insert in the cards */
  private List<Product> mDataset;
  private int mTitle;

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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_product_list, container, false);
    mRecyclerView = (AutofitRecyclerView) v.findViewById(R.id.recycler_view_product_list);
    // RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
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

  private void initDataset() {

    // TEMP synchronous request
    Logger.d("init dataset");
    // launch request
    ApiModel.getInstance().getCategoryProductsAsync(mProductCategory, new Callback<ProductList>() {
      @Override public void success(ProductList productList, Response response) {
        // mock
        for(Header h :response.getHeaders()) {
          Logger.d("Response: name: " + h.getName()+ " value: "+ h.getValue());
        }
        Logger.d("Success response async callback retrofit");
        mDataset = productList.getProducts();
        initAdapter();
      }

      @Override public void failure(RetrofitError error) {

        Logger.d("Error response async callback retrofit"+ error.getMessage());
      }

      //ProductListModel.getInstance().getCategoryProduct(productCategory, getActivity());
    });
  }

  // when the data response will be retrieved ( from db) an event to update UI will be posted.
  @Subscribe
  public void onCategoryProductsReceived(ProductListContentEvent event) {
    Logger.d("onCategoryProductsReceived - ProductListContentEvent - onSuccess product");
    mDataset = event.getProductList().getProducts();
    initAdapter();
  }

  @Subscribe
  public void onNetworkErrorEvent(NetworkErrorEvent event) {
    // TODO implement error view.
  }

  private void initAdapter() {
    Logger.d("mDataset len" + mDataset.size());
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
