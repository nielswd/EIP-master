package com.example.vincent.eip.Network.infos.openinghours;

import com.example.vincent.eip.Network.infos.infoevent.InfoEvents;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface RetrofitInterfaceOpeningHours {
    @Headers( "Content-Type: application/json" )
    @PUT("client/hotelhours")
    Call<OpeningHours> getOpeningHours(@Body RequestBody userClientInfo);
}
