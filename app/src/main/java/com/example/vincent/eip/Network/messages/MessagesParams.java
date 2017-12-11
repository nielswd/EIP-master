package com.example.vincent.eip.Network.messages;

import com.example.vincent.eip.Network.Services;
import com.example.vincent.eip.Network.UserClientInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by iNfecteD on 15/06/2017.
 */

public class MessagesParams implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ListMessages> list;
    private UserClientInfo user;


    public MessagesParams() {
    }

    public List<ListMessages> getList() {
        return list;
    }

    public void setList(List<ListMessages> list) {
        this.list = list;
    }

    public UserClientInfo getUser() {
        return user;
    }

    public void setUser(UserClientInfo info) {
        this.user = info;
    }


}
