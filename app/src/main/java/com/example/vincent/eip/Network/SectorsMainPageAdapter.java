package com.example.vincent.eip.Network;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vincent.eip.Interfaces.OnSectorClick;
import com.example.vincent.eip.R;

import java.util.List;

public class SectorsMainPageAdapter extends RecyclerView.Adapter<SectorsMainPageAdapter.MyViewHolder> {
    private List<Sec> mDataset;
    SendData data = new SendData();
    private OnSectorClick mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(TextView textView, ImageView imageView) {
            super(textView);
            mTextView = textView;
            mImageView = imageView;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SectorsMainPageAdapter(List<Sec> myDataset, OnSectorClick listener) {
        mDataset = myDataset;
        mListener = listener;
        Log.d("Sec myDatasret" , mDataset.toString());
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sectors_item, parent, false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (mDataset != null && mDataset.size() != 0 && mDataset.get(position) != null) {
            holder.textSector.setText(mDataset.get(position).getName());
            if (mDataset.get(position).getName().equals("cuisine")) {
                Log.d("Secteur?", "cuisine");
                holder.imgSector.setImageResource(R.mipmap.food);
            } else {
                Log.d("Secteur?", "autre");
                holder.imgSector.setImageResource(R.mipmap.clean);
            }
            holder.descriptionSector.setText(mDataset.get(position).getDescription());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnSectorClick(mDataset.get(position).getName());
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final TextView textSector;
        private final TextView descriptionSector;
        private final ImageView imgSector;

        public MyViewHolder(final View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            textSector = ((TextView) itemView.findViewById(R.id.textSector));
            imgSector = ((ImageView) itemView.findViewById(R.id.imgSector));
            descriptionSector = ((TextView) itemView.findViewById(R.id.descriptionSector));
        }
    }
}
