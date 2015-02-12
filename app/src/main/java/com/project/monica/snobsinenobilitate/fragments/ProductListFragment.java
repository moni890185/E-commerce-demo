package com.project.monica.snobsinenobilitate.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.adapters.ProductListAdapter;
import com.project.monica.snobsinenobilitate.customrecyclerview.AutofitRecyclerView;
import com.project.monica.snobsinenobilitate.model.Product;

import java.util.ArrayList;
import java.util.Arrays;

///**
// * Activities that contain this fragment must implement the
// * {@link ProductListFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// */
public class ProductListFragment extends Fragment implements ProductListAdapter.OnCustomItemClickListener {

    public interface OnProductListItemListener {
        public void onProductListItemClick(Integer productId);
    }

    private static final String TITLE = "title";

    private AutofitRecyclerView mRecyclerView;

    // Adapter
    private ProductListAdapter mProductListAdapter;

    /* Dataset with the images to insert in the cards */
    private ArrayList<Product> mDataset;
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
        initAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        mRecyclerView = (AutofitRecyclerView) v.findViewById(R.id.recycler_view_product_list);

        // RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductListAdapter);
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
        // MOCK!!!!
        mDataset = new ArrayList<Product>(Arrays.asList(Product.PRODUCTS));
    }

    private void initAdapter() {
        mProductListAdapter = new ProductListAdapter(getActivity(), mDataset, this);
    }

    @Override
    public void onItemClick(Integer productId) {
        if (mProductListItemListener != null) {
            mProductListItemListener.onProductListItemClick(productId);
        }

    }
}
