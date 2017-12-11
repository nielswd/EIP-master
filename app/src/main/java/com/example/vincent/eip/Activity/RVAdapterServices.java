package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.SendRequestCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.request.Req;
import com.example.vincent.eip.Network.request.Requests;
import com.example.vincent.eip.Network.request.RequestRetrofit;
import com.example.vincent.eip.Network.services.Serv;
import com.example.vincent.eip.Network.services.Services;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterServices extends RecyclerView.Adapter<RVAdapterServices.MyViewHolder> {

    private Services services;
    private int expandedPosition = -1;
    private Activity ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameItem, mItemDescription;
        public LinearLayout mItemExpend;
        public CardView mRootItem;

        public MyViewHolder(View view) {
            super(view);
            nameItem = (TextView) view.findViewById(R.id.item_title);
            mItemDescription = (TextView) view.findViewById(R.id.item_description);
            mItemExpend = (LinearLayout) view.findViewById(R.id.llExpandArea);
            mRootItem = (CardView) view.findViewById(R.id.llCardBack);
        }
    }


    public RVAdapterServices(Services services, Context t) {
        this.services = services;
        ctx = (Activity) t;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_openinghours, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Serv newItem = services.getServ().get(position);
        holder.nameItem.setText(newItem.getNameService());
        holder.mItemDescription.setText(newItem.getHours() + "\n" + newItem.getPrice());
        holder.mRootItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // custom dialog
                final Dialog dialog = new Dialog(ctx);
                dialog.setContentView(R.layout.custom_dialog_request);
                //dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.nameService);
                text.setText(newItem.getNameService());
                final EditText userCom = (EditText) dialog.findViewById(R.id.edit);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GlobalClass global=(GlobalClass) ctx.getApplication();
                        UserClientInfo clientInfo = global.userInfos;

                        Req newReq = new Req();
                        newReq.setComClient(userCom.getText().toString());
                        //TODO Automatic ROOM!
                        newReq.setFkNumRoom(Integer.decode(clientInfo.getNumRoom()));
                        newReq.setFkIdServices(newItem.getIdServices());

                        RequestRetrofit request = new RequestRetrofit();
                        request.login(ctx, clientInfo, new SendRequestCallback(){
                            public void sendRequest(Requests requests){
                                dialog.dismiss();
                                //ctx.finish();
                            }
                        }, newReq);
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
    }


    @Override
    public int getItemCount() {
        return services.getServ().size();
    }
}
