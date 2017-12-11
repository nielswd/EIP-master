package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.UpdateRequestCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.request.Req;
import com.example.vincent.eip.Network.request.Requests;
import com.example.vincent.eip.Network.request.RetrofitInterfaceRequest;
import com.example.vincent.eip.Network.request.update.RequestUpdateRetrofit;
import com.example.vincent.eip.R;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public class RVAdapterListRequest extends RecyclerView.Adapter<RVAdapterListRequest.MyViewHolder> {

    private Requests requests;
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


    public RVAdapterListRequest(Requests sectors, Context t) {
        this.requests = sectors;
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
        final Req newItem = requests.getReq().get(position);
        holder.nameItem.setText(Integer.toString(newItem.getFkIdServices()));
        holder.mItemDescription.setText(newItem.getComClient() + "\n" + Integer.toString(newItem.getState()));
        holder.mItemExpend.setVisibility(View.VISIBLE);
        holder.mRootItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newItem.getState() == 0) {
                    //TODO delete
                    newItem.setState(4);
                    GlobalClass global = (GlobalClass) mActivity.getApplication();
                    UserClientInfo clientInfo = global.userInfos;
                    RequestUpdateRetrofit updateReq = new RequestUpdateRetrofit();
                    updateReq.login(mActivity, clientInfo, new UpdateRequestCallback() {
                        @Override
                        public void updateRequest(Requests requests) {
                            Log.d("request deleted", requests.getMessage());
                        }
                    }, newItem);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return requests.getReq().size();
    }
}
