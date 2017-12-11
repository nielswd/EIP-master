package com.example.vincent.eip.Interfaces;

import com.example.vincent.eip.Network.messages.Messages;
import com.example.vincent.eip.Network.request.Requests;

/**
 * Created by iNfecteD on 29/06/2017.
 */

public interface GetMessagesCallback {
    void getMessages(Messages messages);
}
