package com.example.vincent.eip.Network.services;

import com.example.vincent.eip.Network.sectors.Sectors;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface RetrofitInterfaceServices {
    @Headers( "Content-Type: application/json" )
    @PUT("client/service")
    Call<Services> getServices(@Body RequestBody userClientInfo);
}
