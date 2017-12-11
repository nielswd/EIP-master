package com.example.vincent.eip.Network.messages;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("list")
    @Expose
    private java.util.List<ListMessages> listMessages = null;
    @SerializedName("message")
    @Expose
    private String message;

    public java.util.List<ListMessages> getListMessages() {
        return listMessages;
    }

    public void setListMessages(java.util.List<ListMessages> listMessages) {
        this.listMessages = listMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}