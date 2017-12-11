package com.example.vincent.eip.Interfaces;

import com.example.vincent.eip.Network.domotique.DeviceResponse;
import com.example.vincent.eip.Network.messages.Messages;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public interface GetDevicesCallback {
    void getDevices(DeviceResponse devices);
}
