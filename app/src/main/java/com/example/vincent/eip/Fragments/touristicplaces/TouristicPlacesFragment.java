package com.example.vincent.eip.Fragments.touristicplaces;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.eip.Fragments.infoevent.RVAdapterInfoEvent;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.InfoEventsCallback;
import com.example.vincent.eip.Interfaces.TouristicPlacesCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;
import com.example.vincent.eip.Network.infos.touristicplaces.TouristicPlaces;
import com.example.vincent.eip.Network.infos.touristicplaces.TouristicPlacesRetrofit;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class TouristicPlacesFragment extends Fragment {
    private Activity mContainerActivity;
    private RecyclerView recyclerView;
    private RVAdapterTouristicPlaces myRVAdapterTouristicPlaces;
    public TouristicPlacesFragment() {
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


        View fragView = inflater.inflate(R.layout.fragment_touristicplaces, container, false);

//        SendData data = new SendData();
        GlobalClass global=(GlobalClass) mContainerActivity.getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        TouristicPlacesRetrofit transportRetrofit = new TouristicPlacesRetrofit();
        recyclerView = (RecyclerView) fragView.findViewById(R.id.my_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);

        transportRetrofit.login(mContainerActivity, clientInfo, new TouristicPlacesCallback() {
            @Override
            public void getTouristicPlacesCallBack(TouristicPlaces touristicPlaces) {
                if (touristicPlaces != null){
                    myRVAdapterTouristicPlaces = new RVAdapterTouristicPlaces(touristicPlaces);
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
