package com.example.vincent.eip.Fragments.openinghours;

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

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.OpeningHoursCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHours;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHoursRetrofit;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class OpeningHoursFragment extends Fragment {
    private Activity mContainerActivity;
    private RecyclerView recyclerView;
    private RVAdapterOpeningHours myRVAdapterOpeningHours;
    public OpeningHoursFragment() {
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


        View fragView = inflater.inflate(R.layout.fragment_openinghours, container, false);

//        SendData data = new SendData();
        GlobalClass global=(GlobalClass) mContainerActivity.getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        recyclerView = (RecyclerView) fragView.findViewById(R.id.my_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);

        OpeningHoursRetrofit transportRetrofit = new OpeningHoursRetrofit();
        transportRetrofit.login(mContainerActivity, clientInfo, new OpeningHoursCallback() {
            @Override
            public void getOpeningHoursCallBack(OpeningHours openingHours) {
                if (openingHours != null){
                    myRVAdapterOpeningHours = new RVAdapterOpeningHours(openingHours);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContainerActivity);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(myRVAdapterOpeningHours);
                }
            }
        });



        return fragView;
    }
}
