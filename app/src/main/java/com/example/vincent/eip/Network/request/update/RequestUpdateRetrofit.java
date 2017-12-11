package com.example.vincent.eip.Network.request.update;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.vincent.eip.Interfaces.GetRequestCallback;
import com.example.vincent.eip.Interfaces.SendRequestCallback;
import com.example.vincent.eip.Interfaces.UpdateRequestCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.request.Req;
import com.example.vincent.eip.Network.request.RequestParams;
import com.example.vincent.eip.Network.request.Requests;
import com.example.vincent.eip.Network.request.RetrofitInterfaceRequest;
import com.example.vincent.eip.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public class RequestUpdateRetrofit {
    private Activity        activity;
    private UpdateRequestCallback mListener;
    private GetRequestCallback getListener;

    public void login(final Context t,
                      final UserClientInfo clientInfo,
                      final UpdateRequestCallback listener,
                      final Req req) {
        activity = (Activity) t;
        mListener = listener;
        sendRequest(tryCreateUserForLogin(clientInfo, req));
    }


    private JSONObject tryCreateUserForLogin(UserClientInfo clientInfo, Req requests){
        RequestParams params = new RequestParams();
        List<Req> listReq = new ArrayList<>();
        listReq.add(requests);
        params.setUser(clientInfo);
        params.setList(listReq);

        Gson gson = new Gson();
        String jsonString = gson.toJson(params);

        try {
            JSONObject request = new JSONObject(jsonString);
            Log.d("json login", request.toString());
            return request;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendRequest(JSONObject jsonLogin){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        //TODO Change level of Logging before Production! (go NONE)
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(activity.getString(R.string.serverURLRest))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        RetrofitInterfaceRequest service = retrofit.create(RetrofitInterfaceRequest.class);

        Call<Requests> call = service.updateRequest(createRequestBody(jsonLogin));
        call.enqueue(new Callback<Requests>() {
            @Override
            public void onResponse(Call<Requests> call, retrofit2.Response<Requests> response) {
                manageSuccessFulSendRequest(response);
            }

            @Override
            public void onFailure(Call<Requests> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    public RequestBody createRequestBody(JSONObject jsonLogin){
        RequestBody myreqbody = null;
        try {
            myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                    (new JSONObject(String.valueOf(jsonLogin))).toString());
            return myreqbody;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //TODO catch errors
       return null;
    }

    private void manageSuccessFulSendRequest(retrofit2.Response<Requests> response) {
        Log.d("response retrofit", response.toString());
        mListener.updateRequest(response.body());
    }

    private void manageSuccessFulGetRequest(retrofit2.Response<Requests> response) {
        Log.d("response retrofit", response.toString());
        getListener.getRequest(response.body());
    }
}
