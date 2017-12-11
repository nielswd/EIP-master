package com.example.vincent.eip.Network;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iNfecteD on 17/02/2017.
 */

public class SectorResponse {

    private String message = "";
    private List<Sec> secs = new ArrayList<Sec>();

    public SectorResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Sec> getSecs() {
        return secs;
    }

    public void setSecs(List<Sec> secs) {
        this.secs = secs;
    }
}
