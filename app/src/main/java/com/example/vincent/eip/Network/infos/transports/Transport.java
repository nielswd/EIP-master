
package com.example.vincent.eip.Network.infos.transports;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transport implements Serializable
{

    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("idTransports")
    @Expose
    private Integer idTransports;
    @SerializedName("line")
    @Expose
    private String line;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("type")
    @Expose
    private String type;
    private final static long serialVersionUID = 4358085637089298562L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Transport() {
    }

    /**
     * 
     * @param station
     * @param hours
     * @param line
     * @param type
     * @param idTransports
     */
    public Transport(String hours, Integer idTransports, String line, String station, String type) {
        super();
        this.hours = hours;
        this.idTransports = idTransports;
        this.line = line;
        this.station = station;
        this.type = type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Integer getIdTransports() {
        return idTransports;
    }

    public void setIdTransports(Integer idTransports) {
        this.idTransports = idTransports;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
