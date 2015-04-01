package com.project.monica.snobsinenobilitate.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.project.monica.snobsinenobilitate.R;

public class StoreFinderFragment extends BaseFragment {


    public static StoreFinderFragment newInstance() {
        StoreFinderFragment fragment = new StoreFinderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public StoreFinderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_finder, container, false);
    }

}
