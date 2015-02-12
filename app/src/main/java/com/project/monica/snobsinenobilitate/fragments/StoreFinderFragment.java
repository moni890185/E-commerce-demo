package com.project.monica.snobsinenobilitate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.monica.snobsinenobilitate.R;

public class StoreFinderFragment extends Fragment {

    private static final String TITLE = "title";

    private int mTitle;

    public static StoreFinderFragment newInstance(int title) {
        StoreFinderFragment fragment = new StoreFinderFragment();
        Bundle args = new Bundle();
        args.putInt(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public StoreFinderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getInt(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_finder, container, false);
    }

}
