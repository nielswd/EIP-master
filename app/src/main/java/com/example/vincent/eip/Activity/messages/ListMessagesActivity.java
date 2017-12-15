package com.example.vincent.eip.Activity.messages;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincent.eip.Activity.RVAdapterListRequest;
import com.example.vincent.eip.Activity.ViewPagerAdapter;
import com.example.vincent.eip.Activity.messages.MessageReceived.MessagesReceivedFragment;
import com.example.vincent.eip.Activity.messages.MessageSent.MessagesSentFragment;
import com.example.vincent.eip.Fragments.infoevent.InfoEventsFragment;
import com.example.vincent.eip.Fragments.openinghours.OpeningHoursFragment;
import com.example.vincent.eip.Fragments.touristicplaces.TouristicPlacesFragment;
import com.example.vincent.eip.Fragments.transports.TransportsFragment;
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

public class ListMessagesActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager tabsViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Messages");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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

        tabsViewPager = (ViewPager) findViewById(R.id.tabsviewpager);
        setupTabsViewPager(tabsViewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(tabsViewPager);
    }

    private void setupTabsViewPager(ViewPager viewPager) {
        MessagesVPAdapter adapter = new MessagesVPAdapter(getSupportFragmentManager());
        adapter.addFragment(new MessagesReceivedFragment(), "Boite de réception");
        adapter.addFragment(new MessagesSentFragment(), "Messages Envoyés");
        viewPager.arrowScroll(View.FOCUS_LEFT);
        viewPager.arrowScroll(View.FOCUS_RIGHT);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
