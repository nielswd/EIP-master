package com.example.vincent.eip.Fragments.touristicplaces;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.Network.infos.openinghours.OpeningHour;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHours;
import com.example.vincent.eip.Network.infos.touristicplaces.TouristicPlace;
import com.example.vincent.eip.Network.infos.touristicplaces.TouristicPlaces;
import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterTouristicPlaces extends RecyclerView.Adapter<RVAdapterTouristicPlaces.MyViewHolder> {

    private TouristicPlaces touristicPlaces;
    private int expandedPosition = -1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameItem, mItemDescription, mStart;
        public LinearLayout mItemExpend;
        public CardView mRootItem;

        public MyViewHolder(View view) {
            super(view);
            nameItem = (TextView) view.findViewById(R.id.item_title);
            mStart = (TextView) view.findViewById(R.id.item_start);
            mItemDescription = (TextView) view.findViewById(R.id.item_description);
            mItemExpend = (LinearLayout) view.findViewById(R.id.llExpandArea);
            mRootItem = (CardView) view.findViewById(R.id.llCardBack);
        }
    }


    public RVAdapterTouristicPlaces(TouristicPlaces touristicPlaces) {
        this.touristicPlaces = touristicPlaces;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_touristicplaces, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TouristicPlace newItem = touristicPlaces.getList().get(position);
        holder.nameItem.setText(newItem.getName());
        holder.mStart.setText(newItem.getHours());
        holder.mItemDescription.setText(newItem.getDescription());
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
        return touristicPlaces.getList().size();
    }
}
