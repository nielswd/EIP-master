package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vincent.eip.Fragments.infoevent.RVAdapterInfoEvent;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.InfoEventsCallback;
import com.example.vincent.eip.Interfaces.SectorsCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.infoevent.InfoEventRetrofit;
import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;
import com.example.vincent.eip.Network.sectors.Sectors;
import com.example.vincent.eip.Network.sectors.SectorsRetrofit;
import com.example.vincent.eip.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

/**
 * Created by niels on 07/07/2017.
 */

public class SectorActivity extends Activity {
    private RecyclerView recyclerView;
    private RVAdapterSectors myRVAdapterSectors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectors);

        //        SendData data = new SendData();
        GlobalClass global=(GlobalClass) getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        SectorsRetrofit transportRetrofit = new SectorsRetrofit();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(SectorActivity.this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);

        transportRetrofit.login(SectorActivity.this, clientInfo, new SectorsCallback() {
            @Override
            public void getAllSectorsCallBack(Sectors sectors) {
                if (sectors != null){
                    myRVAdapterSectors = new RVAdapterSectors(sectors, SectorActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SectorActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(myRVAdapterSectors);
                }
            }
        });
    }
}
