package com.example.vincent.eip.Fragments;

/**
 * Created by iNfecteD on 23/02/2017.
 */


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.eip.Interfaces.CallbackRequestClick;
import com.example.vincent.eip.Interfaces.OnTaskCompleted;
import com.example.vincent.eip.Network.CleaningServicesListAdapter;
import com.example.vincent.eip.Interfaces.OnSectorClick;
import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.Network.Serv;
import com.example.vincent.eip.Network.ServiceAdapter;
import com.example.vincent.eip.R;

import java.util.List;


public class CallRequestFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SendData data = new SendData();

    private Activity mContainerActivity;

    private CallbackRequestClick mListener;

    public static CallRequestFragment newInstance(int page, String title) {
        CallRequestFragment fragmentFirst = new CallRequestFragment();
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
        mContainerActivity = (Activity) getContext();

        View fragView = inflater.inflate(R.layout.fragment_callrequest, container, false);


        mRecyclerView = (RecyclerView) fragView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContainerActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };

        mRecyclerView.setAdapter(mAdapter);

        return fragView;
    }

    public void showDataCleaning(List<Serv> services, final CallbackRequestClick listener){
        mAdapter = new CleaningServicesListAdapter(services, new OnSectorClick() {
            @Override
            public void OnSectorClick(String idSector) {
                Log.d("test", "test sectro click");
                listener.OnRequestClick();
            }
        });
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(mContainerActivity);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new RecyclerView.Adapter() {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return null;
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                }

                @Override
                public int getItemCount() {
                    return 0;
                }
            };
            mAdapter = new CleaningServicesListAdapter(services, new OnSectorClick() {
                @Override
                public void OnSectorClick(String idSector) {
                    Log.d("test", "test sectro click");
                    listener.OnRequestClick();
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public void showDataFood(List<Serv> services, final CallbackRequestClick listener){
        mListener = listener;
        mAdapter = new ServiceAdapter(services, new OnSectorClick() {
            @Override
            public void OnSectorClick(String idSector) {
            }
        });
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void showDatMessage(){

    }

}

