package com.example.vincent.eip.Network.domotique;

import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface RetrofitInterfaceDevices {
    @Headers( "Content-Type: application/json" )
    @POST("GetDevices/")
    Call<DeviceResponse> getDevices(@Body RequestBody userClientInfo);

    @Headers( "Content-Type: application/json" )
    @POST("Action/")
    Call<DeviceResponse> sendAction(@Body RequestBody userClientInfo);
}
