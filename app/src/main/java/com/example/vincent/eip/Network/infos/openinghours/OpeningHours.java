
package com.example.vincent.eip.Network.infos.openinghours;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningHours implements Serializable
{

    @SerializedName("list")
    @Expose
    private List<OpeningHour> list = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -3746450100952345351L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OpeningHours() {
    }

    /**
     * 
     * @param message
     * @param list
     */
    public OpeningHours(List<OpeningHour> list, String message) {
        super();
        this.list = list;
        this.message = message;
    }

    public java.util.List<OpeningHour> getList() {
        return list;
    }

    public void setList(List<OpeningHour> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
