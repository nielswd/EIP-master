package com.example.vincent.eip.Fragments;

/**
 * Created by iNfecteD on 27/02/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 23/02/2017.
 */


public class PaymentFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SendData data = new SendData();

    private Activity mContainerActivity;
    public PaymentFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static PaymentFragment newInstance(int page, String title) {
        PaymentFragment fragmentFirst = new PaymentFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContainerActivity = (Activity) getContext();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContainerActivity = (Activity) getContext();

        View fragView = inflater.inflate(R.layout.fragment_payment, container, false);
        return fragView;
    }




}

