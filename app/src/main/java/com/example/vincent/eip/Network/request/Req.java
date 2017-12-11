
package com.example.vincent.eip.Network.request;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Req implements Serializable
{

    @SerializedName("comClient")
    @Expose
    private String comClient;
    @SerializedName("comEmployee")
    @Expose
    private String comEmployee;
    @SerializedName("fk_id_services")
    @Expose
    private Integer fkIdServices;
    @SerializedName("fk_num_room")
    @Expose
    private Integer fkNumRoom;
    @SerializedName("idRequest")
    @Expose
    private Integer idRequest;
    @SerializedName("state")
    @Expose
    private Integer state;
    private final static long serialVersionUID = 4451013488556207298L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Req() {
    }

    /**
     * 
     * @param fkIdServices
     * @param comEmployee
     * @param state
     * @param fkNumRoom
     * @param idRequest
     * @param comClient
     */
    public Req(String comClient, String comEmployee, Integer fkIdServices, Integer fkNumRoom, Integer idRequest, Integer state) {
        super();
        this.comClient = comClient;
        this.comEmployee = comEmployee;
        this.fkIdServices = fkIdServices;
        this.fkNumRoom = fkNumRoom;
        this.idRequest = idRequest;
        this.state = state;
    }

    public String getComClient() {
        return comClient;
    }

    public void setComClient(String comClient) {
        this.comClient = comClient;
    }

    public Req withComClient(String comClient) {
        this.comClient = comClient;
        return this;
    }

    public String getComEmployee() {
        return comEmployee;
    }

    public void setComEmployee(String comEmployee) {
        this.comEmployee = comEmployee;
    }

    public Req withComEmployee(String comEmployee) {
        this.comEmployee = comEmployee;
        return this;
    }

    public Integer getFkIdServices() {
        return fkIdServices;
    }

    public void setFkIdServices(Integer fkIdServices) {
        this.fkIdServices = fkIdServices;
    }

    public Req withFkIdServices(Integer fkIdServices) {
        this.fkIdServices = fkIdServices;
        return this;
    }

    public Integer getFkNumRoom() {
        return fkNumRoom;
    }

    public void setFkNumRoom(Integer fkNumRoom) {
        this.fkNumRoom = fkNumRoom;
    }

    public Req withFkNumRoom(Integer fkNumRoom) {
        this.fkNumRoom = fkNumRoom;
        return this;
    }

    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public Req withIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Req withState(Integer state) {
        this.state = state;
        return this;
    }

}
