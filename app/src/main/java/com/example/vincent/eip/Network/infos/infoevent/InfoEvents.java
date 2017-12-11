
package com.example.vincent.eip.Network.infos.infoevent;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoEvents implements Serializable
{

    @SerializedName("list")
    @Expose
    private List<InfoEvent> list = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 4109727016350862153L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public InfoEvents() {
    }

    /**
     * 
     * @param message
     * @param list
     */
    public InfoEvents(List<InfoEvent> list, String message) {
        super();
        this.list = list;
        this.message = message;
    }

    public List<InfoEvent> getList() {
        return list;
    }

    public void setList(List<InfoEvent> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
