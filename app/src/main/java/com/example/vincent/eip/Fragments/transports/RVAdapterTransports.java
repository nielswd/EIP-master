package com.example.vincent.eip.Fragments.transports;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.Network.infos.openinghours.OpeningHour;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHours;
import com.example.vincent.eip.Network.infos.transports.Transport;
import com.example.vincent.eip.Network.infos.transports.Transports;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterTransports extends RecyclerView.Adapter<RVAdapterTransports.MyViewHolder> {

    private Transports transports;
    private int expandedPosition = -1;

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


    public RVAdapterTransports(Transports transports) {
        this.transports = transports;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_openinghours, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Transport newItem = transports.getList().get(position);
        holder.nameItem.setText(newItem.getStation());
        holder.mItemDescription.setText(newItem.getLine() + "\n" + newItem.getHours() + "\n" + newItem.getType());
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
        return transports.getList().size();
    }
}
