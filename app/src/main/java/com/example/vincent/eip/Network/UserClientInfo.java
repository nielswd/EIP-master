package com.example.vincent.eip.Network;

/**
 * Created by Vincent RAGOT on 27/11/2016.
 */

import java.io.Serializable;

public class UserClientInfo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String login;
    private String pwd;
    private String numRoom;
    private String sessionId;

    public UserClientInfo() {

    }

    public UserClientInfo(String login, String pwd, String numRoom, String sessionId) {
        super();
        this.login = login;
        this.pwd = pwd;
        this.numRoom = numRoom;
        this.sessionId = sessionId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(String numRoom) {
        this.numRoom = numRoom;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}