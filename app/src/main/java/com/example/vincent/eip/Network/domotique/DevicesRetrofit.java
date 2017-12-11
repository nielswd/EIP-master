package com.example.vincent.eip.Network.domotique;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;

import com.example.vincent.eip.Interfaces.GetDevicesCallback;
import com.example.vincent.eip.Interfaces.InfoEventsCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;
import com.example.vincent.eip.Network.infos.infoevent.RetrofitInterfaceInfoEvent;
import com.example.vincent.eip.Network.messages.MessagesParams;
import com.example.vincent.eip.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class DevicesRetrofit {
    private Activity        activity;
    private GetDevicesCallback mListener;

    public void login(final Context t,
                      final UserClientInfo clientInfo,
                      final GetDevicesCallback listener) {
        activity = (Activity) t;
        mListener = listener;
        tryLogin(tryCreateUserForLogin(clientInfo));
    }

    public void sendActionDomo(final Context t,
                      final UserClientInfo clientInfo,
                               final Device device,
                      final GetDevicesCallback listener) {
        activity = (Activity) t;
        mListener = listener;
        changeStateDevice(createDevice(clientInfo, device));
    }

    private JSONObject tryCreateUserForLogin(UserClientInfo clientInfo){
        DevicesParams params = new DevicesParams();
        params.setRoom(clientInfo.getNumRoom());
        params.setToken(clientInfo.getLogin());
        params.setTime(createTimeNow());

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

    private JSONObject createDevice(UserClientInfo clientInfo, Device device){
        DevicesSendAction params = new DevicesSendAction();
        params.setRoom(clientInfo.getNumRoom());
        params.setToken(clientInfo.getLogin());
        params.setTime(createTimeNow());
        params.setDeviceNum(device.getId());
        if (device.getState().equals("on")){
            params.setSw("off");
        } else {
            params.setSw("on");
        }


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

    private String createTimeNow() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        return strDate;
    }

    public void changeStateDevice(JSONObject jsonLogin){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        //TODO Change level of Logging before Production! (go NONE)
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(activity.getString(R.string.serverURLDomotique))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        RetrofitInterfaceDevices service = retrofit.create(RetrofitInterfaceDevices.class);

        Call<DeviceResponse> call = service.sendAction(createRequestBody(jsonLogin));
        call.enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, retrofit2.Response<DeviceResponse> response) {
                manageSuccessfulLogin(response);
            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {
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
                .baseUrl(activity.getString(R.string.serverURLDomotique))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        RetrofitInterfaceDevices service = retrofit.create(RetrofitInterfaceDevices.class);

        Call<DeviceResponse> call = service.getDevices(createRequestBody(jsonLogin));
        call.enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, retrofit2.Response<DeviceResponse> response) {
                manageSuccessfulLogin(response);
            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {
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

    private void manageSuccessfulLogin(retrofit2.Response<DeviceResponse> response) {
        Log.d("response retrofit", response.toString());
        mListener.getDevices(response.body());
    }
}
