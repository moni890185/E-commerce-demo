package com.project.monica.snobsinenobilitate.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.adapter.ProductListAdapter;
import com.project.monica.snobsinenobilitate.customrecyclerview.AutofitRecyclerView;
import com.project.monica.snobsinenobilitate.model.Product;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ProductListFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ProductListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProductListFragment extends Fragment implements ProductListAdapter.OnCustomItemClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "title";

    private AutofitRecyclerView mRecyclerView;

    // Mock
    private static int[] imgDrawables = new int[]{R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2, R.drawable.img_card_view_woman1, R.drawable.img_card_view_women, R.drawable.img_card_view_shoes};
    private static double[] productPrices = new double[]{130, 120, 115, 135, 110, 171, 135, 143};
    private static String[] productDescription = new String[]{"Textured chinos","Nappy jacket","Dark sweater","Slim-fit shirt", "Silk shirt","Long suede cape","Cape coat","Taupe mocassin" };
    // Adapter
    private ProductListAdapter mProductListAdapter;

    /* Dataset with the images to insert in the cards */
    private ArrayList<Product> mDataset;


    private int mTitle;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment ProductListFragment.
     */
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
        View v =inflater.inflate(R.layout.fragment_product_list, container, false);
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
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void initDataset() {
        // MOCK!!!!
        mDataset = new ArrayList<Product>();
        for (int i = 0; i < imgDrawables.length && i < productPrices.length; i++) {
            Product p = new Product(productPrices[i], imgDrawables[i], productDescription[i]);
            mDataset.add(p);
        }
    }

    private void initAdapter() {
        mProductListAdapter = new ProductListAdapter(getActivity(), mDataset, this);
    }

    @Override
    public void onClick(int position) {
        if(mListener!=null) {
            mListener.onFragmentInteraction(mDataset.get(position));
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Product p);
    }

}
