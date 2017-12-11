package com.example.vincent.eip.Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ServiceResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String message = "";

    private List<Services> serv = new ArrayList<Services>();

    public ServiceResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Services> getserv() {
        return serv;
    }

    public void setserv(List<Services> serv) {
        this.serv = serv;
    }
}
