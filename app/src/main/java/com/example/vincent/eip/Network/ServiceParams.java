package com.example.vincent.eip.Network;

import com.example.vincent.eip.Network.UserClientInfo;

import java.io.Serializable;
import java.util.List;



public class ServiceParams implements Serializable{

    private static final long serialVersionUID = 1L;
    private UserClientInfo user;
    private List<Services> list;



    public ServiceParams() {
    }



    public UserClientInfo getUser() {
        return user;
    }

    public void setUser(UserClientInfo info) {
        this.user = info;
    }

    public List<Services> getList() {
        return list;
    }

    public void setList(List<Services> list) {
        this.list = list;
    }
}
