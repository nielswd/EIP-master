
package com.example.vincent.eip.Network.infos.transports;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transports implements Serializable
{

    @SerializedName("list")
    @Expose
    private List<Transport> list = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -1472454912487824707L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Transports() {
    }

    /**
     * 
     * @param message
     * @param list
     */
    public Transports(List<Transport> list, String message) {
        super();
        this.list = list;
        this.message = message;
    }

    public List<Transport> getList() {
        return list;
    }

    public void setList(List<Transport> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
