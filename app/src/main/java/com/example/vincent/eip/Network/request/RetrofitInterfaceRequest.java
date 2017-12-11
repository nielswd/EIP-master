package com.example.vincent.eip.Network.request;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface RetrofitInterfaceRequest {
    @Headers( "Content-Type: application/json" )
    @PUT("client/request")
    Call<Requests> getRequest(@Body RequestBody userClientInfo);

    @Headers( "Content-Type: application/json" )
    @PUT("client/request/create")
    Call<Requests> sendRequest(@Body RequestBody userClientInfo);

    @Headers( "Content-Type: application/json" )
    @POST("client/request/update")
    Call<Requests> updateRequest(@Body RequestBody userClientInfo);
}
