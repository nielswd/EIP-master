package com.example.vincent.eip.Fragments.transports;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.eip.Fragments.touristicplaces.RVAdapterTouristicPlaces;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.TouristicPlacesCallback;
import com.example.vincent.eip.Interfaces.TransportsCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.touristicplaces.TouristicPlaces;
import com.example.vincent.eip.Network.infos.transports.TransportRetrofit;
import com.example.vincent.eip.Network.infos.transports.Transports;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class TransportsFragment extends Fragment {
    private Activity mContainerActivity;
    private RecyclerView recyclerView;
    private RVAdapterTransports myRVAdapterTouristicPlaces;
    public TransportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null) {
            mContainerActivity = getActivity();
        } else {
            Log.e("containerActivity", "NULL");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View fragView = inflater.inflate(R.layout.fragment_transport, container, false);

//        SendData data = new SendData();
        GlobalClass global=(GlobalClass) mContainerActivity.getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        final TransportRetrofit transportRetrofit = new TransportRetrofit();

        recyclerView = (RecyclerView) fragView.findViewById(R.id.my_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);

        transportRetrofit.login(mContainerActivity, clientInfo, new TransportsCallback() {
            @Override
            public void getTransportsCallBack(Transports transports) {
                if (transports != null){
                    myRVAdapterTouristicPlaces = new RVAdapterTransports(transports);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContainerActivity);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(myRVAdapterTouristicPlaces);
                }
            }
        });

        return fragView;
    }
}
