package com.example.vincent.eip.Network.login;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface RetrofitInterface {
    @Headers( "Content-Type: application/json" )
    @PUT("auth/client/connect")
    Call<Login> loginUser(@Body RequestBody userClientInfo);
}
