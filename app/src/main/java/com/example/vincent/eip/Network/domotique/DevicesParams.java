package com.example.vincent.eip.Network.domotique;

import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.messages.ListMessages;

import java.io.Serializable;
import java.util.List;

/**
 * Created by iNfecteD on 15/06/2017.
 */

public class DevicesParams implements Serializable {

    private String time;
    private String room;
    private String token;

    public String getTime() {
        return time;
    }

    public  void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
