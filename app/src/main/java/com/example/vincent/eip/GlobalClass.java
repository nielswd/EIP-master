package com.example.vincent.eip;

import android.app.Application;
import android.os.Bundle;

import com.example.vincent.eip.Network.Sec;
import com.example.vincent.eip.Network.SectorParams;
import com.example.vincent.eip.Network.Serv;
import com.example.vincent.eip.Network.ServiceParams;
import com.example.vincent.eip.Network.Services;
import com.example.vincent.eip.Network.UserClientInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalClass extends Application {
    public UserClientInfo userInfos;
    public ServiceParams serviceParams;
    public SectorParams sectorParams;
    public List<Serv> listServices = new ArrayList<Serv>();
    public List<Sec> listSectors = new ArrayList<Sec>();
    public String myVal;
    public Map<String, List<Serv>> listService= new HashMap<String, List<Serv>>();
}