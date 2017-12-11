package com.example.vincent.eip.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 23/02/2017.
 */


public class RequestFragment extends Fragment {

    SendData data = new SendData();

    private Activity mContainerActivity;

    public RequestFragment() {
        // Required empty public constructor
    }
    // newInstance constructor for creating fragment with arguments
    public static RequestFragment newInstance(int page, String title) {
        RequestFragment fragmentFirst = new RequestFragment();
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

        View fragView = inflater.inflate(R.layout.fragment_request, container, false);

        Button btnRequest = (Button) fragView.findViewById(R.id.btnRequest);
        Button btnCancel = (Button) fragView.findViewById(R.id.btnCancel);



        return fragView;
    }



}

