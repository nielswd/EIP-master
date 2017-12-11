package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.Network.infos.infoevent.InfoEvent;
import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;
import com.example.vincent.eip.Network.sectors.Sec;
import com.example.vincent.eip.Network.sectors.Sectors;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterSectors extends RecyclerView.Adapter<RVAdapterSectors.MyViewHolder> {

    private Sectors sectors;
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


    public RVAdapterSectors(Sectors sectors, Context t) {
        this.sectors = sectors;
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
        final Sec newItem = sectors.getSecs().get(position);
        holder.nameItem.setText(newItem.getName());
        holder.mItemDescription.setText(newItem.getDescription());
        holder.mRootItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ServicesActivity.class);
                intent.putExtra("SECTOR_ID", newItem.getName());
                mActivity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return sectors.getSecs().size();
    }
}
