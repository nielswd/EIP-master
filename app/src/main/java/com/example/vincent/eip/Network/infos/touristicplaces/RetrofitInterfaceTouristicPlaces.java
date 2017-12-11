package com.example.vincent.eip.Network.infos.touristicplaces;

import com.example.vincent.eip.Network.infos.openinghours.OpeningHours;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface RetrofitInterfaceTouristicPlaces {
    @Headers( "Content-Type: application/json" )
    @PUT("client/touristicplaces")
    Call<TouristicPlaces> getTouristicPlaces(@Body RequestBody userClientInfo);
}
