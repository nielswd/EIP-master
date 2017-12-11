package com.example.vincent.eip.Network.domotique;

import java.util.List;

/**
 * Created by niels on 17/07/2017.
 */
public class  DeviceResponse {
    private              int code;
    private              String msg;
    private List<Device> devicesList;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Device> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Device> devicesList) {
        this.devicesList = devicesList;
    }
}