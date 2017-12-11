package com.example.vincent.eip.Network.services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.vincent.eip.Interfaces.SectorsCallback;
import com.example.vincent.eip.Interfaces.ServicesCallback;
import com.example.vincent.eip.Network.*;
import com.example.vincent.eip.Network.messages.MessagesParams;
import com.example.vincent.eip.Network.sectors.Sectors;
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


public class ServicesRetrofit {
    private Activity        activity;
    private ServicesCallback mListener;

    public void login(final Context t,
                      final UserClientInfo clientInfo,
                      final String idSector,
                      final ServicesCallback listener) {
        activity = (Activity) t;
        mListener = listener;
        tryLogin(tryCreateUserForLogin(clientInfo, idSector));
    }

    private JSONObject tryCreateUserForLogin(UserClientInfo clientInfo, String idSector){
        ServiceParams params = new ServiceParams();
        List<Serv> listServ = new ArrayList<>();
        Serv newServ = new Serv();
        newServ.setSector(idSector);
        listServ.add(newServ);
        params.setUser(clientInfo);
        params.setList(listServ);

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

    public void tryLogin(JSONObject jsonLogin){
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

        RetrofitInterfaceServices service = retrofit.create(RetrofitInterfaceServices.class);

        Call<Services> call = service.getServices(createRequestBody(jsonLogin));
        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, retrofit2.Response<Services> response) {
                manageSuccessfulLogin(response);
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
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

    private void manageSuccessfulLogin(retrofit2.Response<Services> response) {
        Log.d("response retrofit", response.toString());
        mListener.getServicesCallBack(response.body());
    }
}
