
package com.example.vincent.eip.Network.request;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Requests implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("req")
    @Expose
    private List<Req> req = null;
    private final static long serialVersionUID = 3060464830639622378L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Requests() {
    }

    /**
     * 
     * @param message
     * @param req
     */
    public Requests(String message, List<Req> req) {
        super();
        this.message = message;
        this.req = req;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Requests withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Req> getReq() {
        return req;
    }

    public void setReq(List<Req> req) {
        this.req = req;
    }

    public Requests withReq(List<Req> req) {
        this.req = req;
        return this;
    }

}
