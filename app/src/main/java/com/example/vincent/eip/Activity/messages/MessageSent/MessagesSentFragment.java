package com.example.vincent.eip.Activity.messages.MessageSent;

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

import com.example.vincent.eip.Activity.messages.ListMessagesActivity;
import com.example.vincent.eip.Activity.messages.RVAdapterListMessages;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.GetMessagesCallback;
import com.example.vincent.eip.Interfaces.InfoEventsCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.infoevent.InfoEventRetrofit;
import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;
import com.example.vincent.eip.Network.messages.MessageRetrofit;
import com.example.vincent.eip.Network.messages.Messages;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class MessagesSentFragment extends Fragment {
    private Activity mContainerActivity;
    private RecyclerView recyclerView;
    private MessagesSentAdapter myRVAdapterInfoEvents;
    public MessagesSentFragment() {
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


        View fragView = inflater.inflate(R.layout.fragment_infoevents, container, false);
        //        SendData data = new SendData();
        GlobalClass global=(GlobalClass) mContainerActivity.getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        MessageRetrofit transportRetrofit = new MessageRetrofit();

        recyclerView = (RecyclerView) fragView.findViewById(R.id.my_recycler_view);
        transportRetrofit.login(mContainerActivity, clientInfo, new GetMessagesCallback() {
            @Override
            public void getMessages(Messages messages) {
                if (messages != null){
                    MessagesSentAdapter myAdapter= new MessagesSentAdapter(messages);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContainerActivity);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(myAdapter);
                }
            }
        });


        return fragView;
    }
}
