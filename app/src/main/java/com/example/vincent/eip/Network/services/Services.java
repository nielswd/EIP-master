
package com.example.vincent.eip.Network.services;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("serv")
    @Expose
    private List<Serv> serv = null;
    private final static long serialVersionUID = 4575175127472602630L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Services() {
    }

    /**
     * 
     * @param message
     * @param serv
     */
    public Services(String message, List<Serv> serv) {
        super();
        this.message = message;
        this.serv = serv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Serv> getServ() {
        return serv;
    }

    public void setServ(List<Serv> serv) {
        this.serv = serv;
    }

}
