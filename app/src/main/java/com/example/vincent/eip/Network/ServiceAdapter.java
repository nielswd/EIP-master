package com.example.vincent.eip.Network;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vincent.eip.Interfaces.OnSectorClick;
import com.example.vincent.eip.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {
    private List<Serv> mDataset;
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
    public ServiceAdapter(List<Serv> myDataset, OnSectorClick listener) {
        mDataset = myDataset;
        mListener = listener;
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
            holder.imgSector.setImageResource(R.mipmap.food);
            holder.textSector.setText(mDataset.get(position).getNameService());
            holder.descriptionSector.setText(mDataset.get(position).getHours());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnSectorClick("DSK");
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
