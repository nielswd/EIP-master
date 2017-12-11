package com.example.vincent.eip.Activity.devices;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.GetDevicesCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.domotique.Device;
import com.example.vincent.eip.Network.domotique.DeviceResponse;
import com.example.vincent.eip.Network.domotique.DevicesRetrofit;
import com.example.vincent.eip.Network.messages.ListMessages;
import com.example.vincent.eip.Network.messages.Messages;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterDevices extends RecyclerView.Adapter<RVAdapterDevices.MyViewHolder> {

    private DeviceResponse devices;
    private int expandedPosition = -1;
    private Activity mActivity;

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


    public RVAdapterDevices(DeviceResponse devices, Context t) {
        this.devices = devices;
        this.mActivity = (Activity) t;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_openinghours, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Device newItem = devices.getDevicesList().get(position);
        holder.nameItem.setText(newItem.getName());
        holder.mItemDescription.setText(newItem.getDesc() + "\n" + newItem.getState() + "\n" +
        newItem.getId());
        holder.mItemExpend.setVisibility(View.VISIBLE);
        holder.mRootItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalClass global=(GlobalClass) mActivity.getApplication();
                UserClientInfo clientInfo = global.userInfos;
                DevicesRetrofit devicesRetrofit = new DevicesRetrofit();
                devicesRetrofit.sendActionDomo(mActivity, clientInfo, newItem, new GetDevicesCallback() {
                    @Override
                    public void getDevices(DeviceResponse devices) {
                        Toast.makeText(mActivity, "Device answered", Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(mActivity, "Action sent to device", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return devices.getDevicesList().size();
    }
}
