
package com.example.vincent.eip.Network.infos.touristicplaces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TouristicPlace implements Serializable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("idTouristicPlace")
    @Expose
    private Integer idTouristicPlace;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -2961890490998369343L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TouristicPlace() {
    }

    /**
     * 
     * @param idTouristicPlace
     * @param hours
     * @param description
     * @param name
     * @param link
     */
    public TouristicPlace(String description, String hours, Integer idTouristicPlace, String link, String name) {
        super();
        this.description = description;
        this.hours = hours;
        this.idTouristicPlace = idTouristicPlace;
        this.link = link;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Integer getIdTouristicPlace() {
        return idTouristicPlace;
    }

    public void setIdTouristicPlace(Integer idTouristicPlace) {
        this.idTouristicPlace = idTouristicPlace;
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
