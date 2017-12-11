package com.example.vincent.eip.Network;

import java.util.List;

/**
 * Created by iNfecteD on 17/02/2017.
 */

public class SectorParams {
    private UserClientInfo user;
    private List<Sector> body;

    public SectorParams() {}

    public UserClientInfo getUser() {
        return user;
    }

    public void setUser(UserClientInfo user) {
        this.user = user;
    }

    public List<Sector> getList() {
        return body;
    }

    public void setList(List<Sector> body) {
        this.body = body;
    }
}
