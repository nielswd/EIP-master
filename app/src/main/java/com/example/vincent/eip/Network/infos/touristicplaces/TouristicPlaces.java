
package com.example.vincent.eip.Network.infos.touristicplaces;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TouristicPlaces implements Serializable
{

    @SerializedName("list")
    @Expose
    private List<TouristicPlace> list = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 6460654045985181057L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TouristicPlaces() {
    }

    /**
     * 
     * @param message
     * @param list
     */
    public TouristicPlaces(List<TouristicPlace> list, String message) {
        super();
        this.list = list;
        this.message = message;
    }

    public List<TouristicPlace> getList() {
        return list;
    }

    public void setList(List<TouristicPlace> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
