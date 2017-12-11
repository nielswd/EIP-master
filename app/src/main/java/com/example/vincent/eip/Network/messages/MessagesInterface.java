package com.example.vincent.eip.Network.messages;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by iNfecteD on 08/06/2017.
 */

public interface MessagesInterface {
    @Headers( "Content-Type: application/json" )
    @PUT("client/message")
    Call<Messages> getMessages(@Body RequestBody userClientInfo);

    @Headers( "Content-Type: application/json" )
    @PUT("client/message/messagesend")
    Call<Messages> getSentMessages(@Body RequestBody userClientInfo);

    @Headers( "Content-Type: application/json" )
    @PUT("client/message/create")
    Call<Messages> sendMessages(@Body RequestBody userClientInfo);
}
