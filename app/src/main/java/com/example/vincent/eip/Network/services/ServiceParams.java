package com.example.vincent.eip.Network.services;

import com.example.vincent.eip.Network.Services;
import com.example.vincent.eip.Network.UserClientInfo;

import java.io.Serializable;
import java.util.List;


public class ServiceParams implements Serializable{

    private static final long serialVersionUID = 1L;
    private UserClientInfo user;
    private List<Serv> list;



    public ServiceParams() {
    }

    public UserClientInfo getUser() {
        return user;
    }

    public void setUser(UserClientInfo info) {
        this.user = info;
    }

    public List<Serv> getList() {
        return list;
    }

    public void setList(List<Serv> list) {
        this.list = list;
    }
}
