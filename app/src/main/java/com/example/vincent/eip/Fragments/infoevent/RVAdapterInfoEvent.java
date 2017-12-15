package com.example.vincent.eip.Fragments.infoevent;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.Network.infos.infoevent.InfoEvent;
import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHour;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHours;
import com.example.vincent.eip.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterInfoEvent extends RecyclerView.Adapter<RVAdapterInfoEvent.MyViewHolder> {

    private InfoEvents infoEvents;
    private int expandedPosition = -1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameItem, mItemDescription,mItemStart,mItemEnd,mItemHour;
        public LinearLayout mItemExpend;
        public CardView mRootItem;

        public MyViewHolder(View view) {
            super(view);
            nameItem = (TextView) view.findViewById(R.id.item_title);
            mItemDescription = (TextView) view.findViewById(R.id.item_description);
            mItemStart = (TextView) view.findViewById(R.id.item_start);
            mItemEnd = (TextView) view.findViewById(R.id.item_end);
            mItemHour = (TextView) view.findViewById(R.id.item_hours);
            mItemExpend = (LinearLayout) view.findViewById(R.id.llExpandArea);
            mRootItem = (CardView) view.findViewById(R.id.llCardBack);
        }
    }


    public RVAdapterInfoEvent(InfoEvents infoEvents) {
        this.infoEvents = infoEvents;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_infoevent, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        InfoEvent newItem = infoEvents.getList().get(position);
        holder.nameItem.setText(newItem.getName());
        holder.mItemStart.setText(newItem.getBeginDateEvent());
        holder.mItemEnd.setText(newItem.getEndDateEvent());
        holder.mItemHour.setText(newItem.getHours());
        holder.mItemDescription.setText(newItem.getDescription());
    }


    @Override
    public int getItemCount() {
        return infoEvents.getList().size();
    }
}
