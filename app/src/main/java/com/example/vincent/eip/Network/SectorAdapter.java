package com.example.vincent.eip.Network;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vincent.eip.R;

import java.util.List;

public class SectorAdapter extends RecyclerView.Adapter<SectorAdapter.MyViewHolder> {
    private List<Serv> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SectorAdapter(List<Serv> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.servicesview, parent, false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameService.setText(mDataset.get(position).getNameService());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final TextView nameService;
        private final TextView sector;
        private final TextView price;
        private final TextView hours;

        public MyViewHolder(final View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            nameService = ((TextView) itemView.findViewById(R.id.nameService));
            sector = ((TextView) itemView.findViewById(R.id.Sector));
            price = ((TextView) itemView.findViewById(R.id.Price));
            hours = ((TextView) itemView.findViewById(R.id.Hours));
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sector.getVisibility() == View.GONE) {
                        sector.setVisibility(View.VISIBLE);
                        price.setVisibility(View.VISIBLE);
                        hours.setVisibility(View.VISIBLE);
                    } else {
                        sector.setVisibility(View.GONE);
                        price.setVisibility(View.GONE);
                        hours.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
