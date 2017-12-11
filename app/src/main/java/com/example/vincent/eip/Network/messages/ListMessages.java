package com.example.vincent.eip.Network.messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMessages {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("idMessage")
    @Expose
    private Integer idMessage;
    @SerializedName("objet")
    @Expose
    private String objet;
    @SerializedName("rooms")
    @Expose
    private java.util.List<Object> rooms = null;
    @SerializedName("unread")
    @Expose
    private Boolean unread;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public java.util.List<Object> getRooms() {
        return rooms;
    }

    public void setRooms(java.util.List<Object> rooms) {
        this.rooms = rooms;
    }

    public Boolean getUnread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

}

