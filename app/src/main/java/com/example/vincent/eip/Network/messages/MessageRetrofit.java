package com.example.vincent.eip.Network.messages;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.vincent.eip.Interfaces.GetMessagesCallback;
import com.example.vincent.eip.Interfaces.LoginCallback;
import com.example.vincent.eip.Interfaces.SendMessageCallback;
import com.example.vincent.eip.Network.ServiceParams;
import com.example.vincent.eip.Network.Services;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.request.Req;
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

import static android.R.id.message;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public class MessageRetrofit {
    private Activity        activity;
    private GetMessagesCallback mListener;
    private SendMessageCallback sendListener;

    public void login(final Context t,
                      final UserClientInfo clientInfo,
                      final GetMessagesCallback listener) {
        activity = (Activity) t;
        mListener= listener;
        tryLogin(tryCreateUserForLogin(clientInfo));
    }

    public void getSent(final Context t,
                      final UserClientInfo clientInfo,
                      final GetMessagesCallback listener) {
        activity = (Activity) t;
        mListener= listener;
        getSentMessages(tryCreateUserForLogin(clientInfo));
    }

    public void sendMessage(final Context t,
                            final UserClientInfo clientInfo,
                            final SendMessageCallback listener,
                            final ListMessages newMessage) {
        activity = (Activity) t;
        sendListener= listener;
        sendMessages(tryCreateJsonSendMessage(clientInfo, newMessage));
    }


    private JSONObject tryCreateJsonSendMessage(UserClientInfo clientInfo, ListMessages newMessage){
        MessagesParams params = new MessagesParams();
        List<ListMessages> listReq = new ArrayList<>();
        listReq.add(newMessage);
        params.setList(listReq);
        params.setUser(clientInfo);

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

    private JSONObject tryCreateUserForLogin(UserClientInfo clientInfo){
        MessagesParams params = new MessagesParams();
        params.setUser(clientInfo);

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
    public void sendMessages(JSONObject jsonLogin){
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

        MessagesInterface service = retrofit.create(MessagesInterface.class);

        Call<Messages> call = service.sendMessages(createRequestBody(jsonLogin));
        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, retrofit2.Response<Messages> response) {
                manageSuccessfulMessageSent(response);
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getSentMessages(JSONObject jsonLogin){
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

        MessagesInterface service = retrofit.create(MessagesInterface.class);

        Call<Messages> call = service.getSentMessages(createRequestBody(jsonLogin));
        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, retrofit2.Response<Messages> response) {
                manageSuccessfulLogin(response);
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                t.printStackTrace();
            }
        });
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

        MessagesInterface service = retrofit.create(MessagesInterface.class);

        Call<Messages> call = service.getMessages(createRequestBody(jsonLogin));
        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, retrofit2.Response<Messages> response) {
                manageSuccessfulLogin(response);
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
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

    private void manageSuccessfulLogin(retrofit2.Response<Messages> response) {
        Log.d("response messages", response.toString());
        mListener.getMessages(response.body());
    }

    private void manageSuccessfulMessageSent(retrofit2.Response<Messages> response) {
        Log.d("response messages", response.toString());
        sendListener.sendMessage(response.body());
    }
}
