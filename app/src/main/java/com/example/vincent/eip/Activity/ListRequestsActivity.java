package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.GetRequestCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.request.Requests;
import com.example.vincent.eip.Network.request.RequestRetrofit;
import com.example.vincent.eip.R;

/**
 * Created by niels on 07/07/2017.
 */

public class ListRequestsActivity extends Activity {
    private RecyclerView recyclerView;
    private RVAdapterListRequest rvAdapterListRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectors);

        //        SendData data = new SendData();
        GlobalClass global=(GlobalClass) getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        RequestRetrofit transportRetrofit = new RequestRetrofit();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        transportRetrofit.getRequests(ListRequestsActivity.this, clientInfo, new GetRequestCallback() {
            @Override
            public void getRequest(Requests requests) {
                if (requests != null){
                    rvAdapterListRequest = new RVAdapterListRequest(requests, ListRequestsActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListRequestsActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rvAdapterListRequest);
                }
            }
        });
    }
}
