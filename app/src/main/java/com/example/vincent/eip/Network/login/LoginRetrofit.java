package com.example.vincent.eip.Network.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.LoginCallback;
import com.example.vincent.eip.Network.UserClientInfo;
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

public class LoginRetrofit {
    private Activity        activity;
    private String user;
    private LoginCallback   mLoginListener;
    private SharedPreferences sharedpreferences;
    private UserClientInfo clientInfo;

    public void login(final Context t,
                      final String username, final String com, final String room,
                      final LoginCallback loginListener) {
        activity = (Activity) t;
        mLoginListener = loginListener;
        tryLogin(tryCreateUserForLogin(username, com, room));
    }

    private JSONObject tryCreateUserForLogin(String username, String com, String room){
        clientInfo = new UserClientInfo();
        clientInfo.setLogin(username);
        clientInfo.setPwd(com);
        clientInfo.setNumRoom(room);
        Gson gson = new Gson();
        String jsonString = gson.toJson(clientInfo);
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

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Call<Login> call = service.loginUser(createRequestBody(jsonLogin));
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                try {
                    if (response.body().getCode().equals("200")){
                        manageSuccessfulLogin(response);
                    } else {
                        mLoginListener.OnLoginAchevied(false);
                    }
                } catch (NullPointerException e){
                    mLoginListener.OnLoginAchevied(false);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                t.printStackTrace();
                mLoginListener.OnLoginAchevied(false);
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

    private void manageSuccessfulLogin(retrofit2.Response<Login> response) {

        Log.d("response retrofit", response.body().getCode() + " " + response.body().getSessionId());
        clientInfo.setSessionId(response.body().getSessionId());
        GlobalClass global = (GlobalClass) activity.getApplication();
        global.userInfos = clientInfo;
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("user", user);
        editor.apply();
        mLoginListener.OnLoginAchevied(true);
    }
}
