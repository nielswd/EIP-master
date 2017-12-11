package com.example.vincent.eip.Network.infos.touristicplaces;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.vincent.eip.Interfaces.TouristicPlacesCallback;
import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.infos.openinghours.OpeningHours;
import com.example.vincent.eip.Network.messages.MessagesParams;
import com.example.vincent.eip.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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

public class TouristicPlacesRetrofit {
    private Activity        activity;
    private TouristicPlacesCallback mListener;

    public void login(final Context t,
                      final UserClientInfo clientInfo,
                      final TouristicPlacesCallback listener) {
        activity = (Activity) t;
        mListener = listener;
        tryLogin(tryCreateUserForLogin(clientInfo));
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

        RetrofitInterfaceTouristicPlaces service = retrofit.create(RetrofitInterfaceTouristicPlaces.class);

        Call<TouristicPlaces> call = service.getTouristicPlaces(createRequestBody(jsonLogin));
        call.enqueue(new Callback<TouristicPlaces>() {
            @Override
            public void onResponse(Call<TouristicPlaces> call, retrofit2.Response<TouristicPlaces> response) {
                manageSuccessfulLogin(response);
            }

            @Override
            public void onFailure(Call<TouristicPlaces> call, Throwable t) {
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

    private void manageSuccessfulLogin(retrofit2.Response<TouristicPlaces> response) {
        Log.d("response retrofit", response.toString());
        mListener.getTouristicPlacesCallBack(response.body());
    }
}
