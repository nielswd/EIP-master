package com.example.vincent.eip.Network.login;


import org.json.JSONArray;
import org.json.JSONObject;

public class LoginResult {

    private String code;
    private String sessionId;
    private JSONObject client;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public JSONObject getClient(){ return client;}

    public void setClient(JSONObject client){ this.client = client;}

}