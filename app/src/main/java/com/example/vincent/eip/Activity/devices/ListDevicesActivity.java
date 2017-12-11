package com.example.vincent.eip.Activity.devices;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vincent.eip.Activity.RVAdapterListRequest;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.GetDevicesCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.domotique.DeviceResponse;
import com.example.vincent.eip.Network.domotique.DevicesRetrofit;
import com.example.vincent.eip.R;

/**
 * Created by niels on 07/07/2017.
 */

public class ListDevicesActivity extends Activity {
    private RecyclerView recyclerView;
    private RVAdapterDevices rvAdapterListRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectors);

        //        SendData data = new SendData();
        GlobalClass global=(GlobalClass) getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        DevicesRetrofit transportRetrofit = new DevicesRetrofit();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        transportRetrofit.login(ListDevicesActivity.this, clientInfo, new GetDevicesCallback() {
            @Override
            public void getDevices(DeviceResponse devices) {
                if (devices != null){
                    rvAdapterListRequest = new RVAdapterDevices(devices, ListDevicesActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListDevicesActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rvAdapterListRequest);
                }
            }
        });
    }
}
