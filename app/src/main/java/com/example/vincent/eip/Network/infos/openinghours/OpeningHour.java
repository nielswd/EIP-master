
package com.example.vincent.eip.Network.infos.openinghours;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningHour implements Serializable
{

    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("entity")
    @Expose
    private String entity;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("idHotelHours")
    @Expose
    private Integer idHotelHours;
    private final static long serialVersionUID = 1383996658754617969L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OpeningHour() {
    }

    /**
     * 
     * @param idHotelHours
     * @param hours
     * @param entity
     * @param comments
     */
    public OpeningHour(String comments, String entity, String hours, Integer idHotelHours) {
        super();
        this.comments = comments;
        this.entity = entity;
        this.hours = hours;
        this.idHotelHours = idHotelHours;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Integer getIdHotelHours() {
        return idHotelHours;
    }

    public void setIdHotelHours(Integer idHotelHours) {
        this.idHotelHours = idHotelHours;
    }

}
