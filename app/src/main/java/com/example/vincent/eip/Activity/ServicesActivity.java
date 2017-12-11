package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.OnTaskCompleted;
import com.example.vincent.eip.Interfaces.SectorsCallback;
import com.example.vincent.eip.Interfaces.ServicesCallback;
import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.sectors.Sectors;
import com.example.vincent.eip.Network.sectors.SectorsRetrofit;
import com.example.vincent.eip.Network.services.Services;
import com.example.vincent.eip.Network.services.ServicesRetrofit;
import com.example.vincent.eip.R;


public class ServicesActivity extends Activity {
    private RecyclerView recyclerView;
    private RVAdapterServices rvAdapterServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        String s = getIntent().getStringExtra("SECTOR_ID");
                SendData data = new SendData();
        GlobalClass global=(GlobalClass) getApplication();
        UserClientInfo clientInfo = global.userInfos;

        ServicesRetrofit transportRetrofit = new ServicesRetrofit();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        transportRetrofit.login(ServicesActivity.this, clientInfo, s, new ServicesCallback() {
            @Override
            public void getServicesCallBack(Services services) {
                if (services != null){
                    rvAdapterServices = new RVAdapterServices(services, ServicesActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ServicesActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rvAdapterServices);
                }
            }
        });

    }
}
