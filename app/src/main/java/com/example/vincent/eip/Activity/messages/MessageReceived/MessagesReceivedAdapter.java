package com.example.vincent.eip.Activity.messages.MessageReceived;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.Network.messages.ListMessages;
import com.example.vincent.eip.Network.messages.Messages;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class MessagesReceivedAdapter extends RecyclerView.Adapter<MessagesReceivedAdapter.MyViewHolder> {

    private Messages infoEvents;
    private int expandedPosition = -1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mMessage, mOrigin, mDate, mObject;
        public LinearLayout mItemExpend;
        public CardView mRootItem;

        public MyViewHolder(View view) {
            super(view);
            mMessage = (TextView) view.findViewById(R.id.item_message);
            mOrigin = (TextView) view.findViewById(R.id.item_origin);
            mDate = (TextView) view.findViewById(R.id.item_date);
            mObject = (TextView) view.findViewById(R.id.item_object);
            mItemExpend = (LinearLayout) view.findViewById(R.id.llExpandArea);
            mRootItem = (CardView) view.findViewById(R.id.llCardBack);
        }
    }


    public MessagesReceivedAdapter(Messages infoEvents) {
        this.infoEvents = infoEvents;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_messages, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ListMessages newItem = infoEvents.getListMessages().get(position);
        holder.mMessage.setText(newItem.getBody());
        holder.mObject.setText(newItem.getObjet());
        holder.mOrigin.setText("Hôtel");
        holder.mDate.setText(newItem.getDate());
    }


    @Override
    public int getItemCount() {
        return infoEvents.getListMessages().size();
    }
}
