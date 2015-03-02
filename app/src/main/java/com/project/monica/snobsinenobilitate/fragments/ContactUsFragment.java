package com.project.monica.snobsinenobilitate.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.project.monica.snobsinenobilitate.R;

///**
//* A simple {@link Fragment} subclass.
//* Activities that contain this fragment must implement the
//* {@link ContactUsFragment.OnFragmentInteractionListener} interface
//* to handle interaction events.
//* Use the {@link ContactUsFragment#newInstance} factory method to
//* create an instance of this fragment.
//*/
public class ContactUsFragment extends BaseFragment {

    private static final String TITLE = "title";

    private int mTitle;

    public static ContactUsFragment newInstance(int title) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putInt(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactUsFragment() {
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
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }



}
