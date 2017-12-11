package com.example.vincent.eip.Activity.messages;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.Network.messages.ListMessages;
import com.example.vincent.eip.Network.messages.Messages;
import com.example.vincent.eip.Network.request.Req;
import com.example.vincent.eip.Network.request.Requests;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterListMessages extends RecyclerView.Adapter<RVAdapterListMessages.MyViewHolder> {

    private Messages messages;
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


    public RVAdapterListMessages(Messages messages, Context t) {
        this.messages = messages;
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
        final ListMessages newItem = messages.getListMessages().get(position);
        holder.nameItem.setText(newItem.getObjet());
        holder.mItemDescription.setText(newItem.getDate() + "\n" + newItem.getBody());
        holder.mRootItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mItemExpend.getVisibility() == View.GONE){
                    holder.mItemExpend.setVisibility(View.VISIBLE);
                } else {
                    holder.mItemExpend.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return messages.getListMessages().size();
    }
}
