
package com.example.vincent.eip.Network.infos.infoevent;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoEvent implements Serializable
{

    @SerializedName("beginDateEvent")
    @Expose
    private String beginDateEvent;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("endDateEvent")
    @Expose
    private String endDateEvent;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("idInfoEvent")
    @Expose
    private Integer idInfoEvent;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 5236786319180943248L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public InfoEvent() {
    }

    /**
     * 
     * @param beginDateEvent
     * @param hours
     * @param description
     * @param name
     * @param link
     * @param idInfoEvent
     * @param endDateEvent
     */
    public InfoEvent(String beginDateEvent, String description, String endDateEvent, String hours, Integer idInfoEvent, String link, String name) {
        super();
        this.beginDateEvent = beginDateEvent;
        this.description = description;
        this.endDateEvent = endDateEvent;
        this.hours = hours;
        this.idInfoEvent = idInfoEvent;
        this.link = link;
        this.name = name;
    }

    public String getBeginDateEvent() {
        return beginDateEvent;
    }

    public void setBeginDateEvent(String beginDateEvent) {
        this.beginDateEvent = beginDateEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDateEvent() {
        return endDateEvent;
    }

    public void setEndDateEvent(String endDateEvent) {
        this.endDateEvent = endDateEvent;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Integer getIdInfoEvent() {
        return idInfoEvent;
    }

    public void setIdInfoEvent(Integer idInfoEvent) {
        this.idInfoEvent = idInfoEvent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
