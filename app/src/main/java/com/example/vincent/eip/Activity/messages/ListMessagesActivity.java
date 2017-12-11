package com.example.vincent.eip.Activity.messages;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincent.eip.Activity.RVAdapterListRequest;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.GetMessagesCallback;
import com.example.vincent.eip.Interfaces.GetRequestCallback;
import com.example.vincent.eip.Interfaces.SendMessageCallback;
import com.example.vincent.eip.Interfaces.SendRequestCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.messages.ListMessages;
import com.example.vincent.eip.Network.messages.MessageRetrofit;
import com.example.vincent.eip.Network.messages.Messages;
import com.example.vincent.eip.Network.request.Req;
import com.example.vincent.eip.Network.request.RequestRetrofit;
import com.example.vincent.eip.Network.request.Requests;
import com.example.vincent.eip.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by niels on 07/07/2017.
 */

public class ListMessagesActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RVAdapterListMessages rvAdapterListMessages;
    private RVAdapterListMessages rvAdapterListMessages2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        FloatingActionButton newMessage = (FloatingActionButton) findViewById(R.id.new_message);
        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(ListMessagesActivity.this);
                dialog.setContentView(R.layout.custom_dialog_messages);
                //dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                final EditText userCom = (EditText) dialog.findViewById(R.id.edit);
                final EditText userObj  = (EditText) dialog.findViewById(R.id.idObjet);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userObj.getText().toString().length() > 0 && userCom.getText().toString().length() > 0) {
                            GlobalClass global = (GlobalClass) ListMessagesActivity.this.getApplication();
                            UserClientInfo clientInfo = global.userInfos;

                            ListMessages newReq = new ListMessages();
                            newReq.setBody(userCom.getText().toString());
                            newReq.setObjet(userObj.getText().toString());
                            newReq.setUnread(false);
                            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            Date date = new Date();
                            String datetime = dateformat.format(date);
                            newReq.setDate(datetime);

                            //TODO Automatic ROOM!
                            newReq.setRooms(new ArrayList<Object>());

                            MessageRetrofit request = new MessageRetrofit();
                            request.sendMessage(ListMessagesActivity.this, clientInfo, new SendMessageCallback() {
                                public void sendMessage(Messages messages) {
                                    dialog.dismiss();
                                    //ListMessagesActivity.this.finish();
                                }
                            }, newReq);
                        } else {
                            Toast.makeText(ListMessagesActivity.this, "Merci de renseigner un objet et un message", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogButtonCANCEL);
                // if button is clicked, close the custom dialog
                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        //        SendData data = new SendData();
        GlobalClass global=(GlobalClass) getApplication();
        UserClientInfo clientInfo = global.userInfos;
//        data.getTransports(mContainerActivity, clientInfo);

        MessageRetrofit transportRetrofit = new MessageRetrofit();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        transportRetrofit.login(ListMessagesActivity.this, clientInfo, new GetMessagesCallback() {
            @Override
            public void getMessages(Messages messages) {
                if (messages != null){
                    rvAdapterListMessages = new RVAdapterListMessages(messages, ListMessagesActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListMessagesActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rvAdapterListMessages);
                }
            }
        });

        recyclerView2 = (RecyclerView) findViewById(R.id.my_recycler_view2);
        MessageRetrofit transportRetrofit2 = new MessageRetrofit();
        transportRetrofit2.getSent(ListMessagesActivity.this, clientInfo, new GetMessagesCallback() {
            @Override
            public void getMessages(Messages messages2) {
                if (messages2 != null){
                    rvAdapterListMessages2 = new RVAdapterListMessages(messages2, ListMessagesActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListMessagesActivity.this);
                    recyclerView2.setLayoutManager(mLayoutManager);
                    recyclerView2.setItemAnimator(new DefaultItemAnimator());
                    recyclerView2.setAdapter(rvAdapterListMessages2);
                }
            }
        });

    }
}
