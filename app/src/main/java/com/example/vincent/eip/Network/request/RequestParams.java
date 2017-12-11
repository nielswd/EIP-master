package com.example.vincent.eip.Network.request;

import com.example.vincent.eip.Network.UserClientInfo;
import com.example.vincent.eip.Network.services.Serv;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;


public class RequestParams implements Serializable{

    private static final long serialVersionUID = 1L;
    private UserClientInfo user;
    private List<Req> list;



    public RequestParams() {
    }

    public UserClientInfo getUser() {
        return user;
    }

    public void setUser(UserClientInfo info) {
        this.user = info;
    }

    public List<Req> getList() {
        return list;
    }

    public void setList(List<Req> list) {
        this.list = list;
    }
}
