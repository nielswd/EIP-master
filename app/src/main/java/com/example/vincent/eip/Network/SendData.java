package com.example.vincent.eip.Network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vincent.eip.GlobalClass;
import com.example.vincent.eip.Interfaces.InfosCallback;
import com.example.vincent.eip.Interfaces.LoginCallback;
import com.example.vincent.eip.Interfaces.OnTaskCompleted;
import com.example.vincent.eip.Network.login.LoginResult;
import com.example.vincent.eip.Network.messages.MessageRetrofit;
import com.example.vincent.eip.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niels on 15/02/2016.
 */
public class SendData {
    private Activity activity;
    private int login_ok = 0;
    String user;
    SharedPreferences sharedpreferences;
    UserClientInfo clientInfo;
    private List<Serv> servicesGlobal = new ArrayList<Serv>();
    private OnTaskCompleted mListener;
    private LoginCallback mLoginListener;

    private String createUserForLogin(String username, String com, String room){
        UserClientInfo clientInfo = new UserClientInfo();
        clientInfo.setLogin(username);
        clientInfo.setPwd(com);
        clientInfo.setNumRoom(room);
        Gson gson = new Gson();
        String jsonString = gson.toJson(clientInfo);
        try {
            JSONObject request = new JSONObject(jsonString);
            Log.d("json login", request.toString());
            return request.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public void login(final Context t, final String username, final String com, final String room, final LoginCallback loginListener) {

        activity = (Activity) t;
        mLoginListener = loginListener;
        RequestQueue queue = Volley.newRequestQueue(t);

        clientInfo = new UserClientInfo();
        clientInfo.setLogin(username);
        clientInfo.setPwd(com);
        clientInfo.setNumRoom(room);

        final String data = createUserForLogin(username, com, room);
        String REST_API_URL = activity.getString(R.string.serverURLRest) + "auth/client/connect";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                            ParserJson<LoginResult> pj = new ParserJson<>();

                            LoginResult loginResult = pj.writeObject(response, LoginResult.class);
                        if (loginResult.getCode().equals("200")){
                            clientInfo.setSessionId(loginResult.getSessionId());
                            GlobalClass global = (GlobalClass) activity.getApplication();
                            global.userInfos = clientInfo;
                            getAllSectors(t, clientInfo, new OnTaskCompleted() {
                                @Override
                                public void onTaskCompleted() {

                                }
                            });

                            //getOpeningHours(t, clientInfo);
                            //getTransports(t, clientInfo);
                            //getInfoEvent(t, clientInfo);
                            //getTouristicPlaces(t, clientInfo);
                            //getMessages(t, clientInfo);
//                            MessageRetrofit testMessages = new MessageRetrofit();
//                            testMessages.login(activity, clientInfo);
                            saveResponse(response, activity);
                        } else {
                            mLoginListener.OnLoginAchevied(false);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        try {
            Log.d("REQUEST VOLLEY", myReq.getBody().toString());
        } catch (AuthFailureError e){
            e.printStackTrace();
        }
        queue.add(myReq);

    }

    private void saveResponse(String response,Activity activity) {
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("user", user);
        editor.apply();
        mLoginListener.OnLoginAchevied(true);
    }

    public int get_Log(){
        return login_ok;
    }


    public void getAllServices(Context t, final UserClientInfo userClientInfo) {
        activity = (Activity) t;

        RequestQueue queue = Volley.newRequestQueue(t);
        ServiceParams params = new ServiceParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

       // getAllSectors(t, userClientInfo);
        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/service";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                        ParserJson<ServicesResult> pj = new ParserJson<>();

                        ServicesResult servicesResult = new ServicesResult();

                        servicesResult = pj.writeObject(response, ServicesResult.class);
                        GlobalClass global=(GlobalClass)activity.getApplication();

                        for (Serv serv: servicesResult.getServ()
                             ) {
                            global.listServices.add(serv);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }

    public List getListServices(){
        return servicesGlobal;
    }


    public void getAllSectors(Context t, final UserClientInfo userClientInfo, OnTaskCompleted listener){
        activity = (Activity) t;
        RequestQueue queue = Volley.newRequestQueue(t);
        SectorParams params = new SectorParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.sectorParams=params;
        ParserJson<SectorParams> pj = new ParserJson<>(params);
        mListener=listener;

        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/sector";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                        ParserJson<SectorResponse> pj = new ParserJson<>();

                        SectorResponse sectorResponse = new SectorResponse();

                        sectorResponse = pj.writeObject(response, SectorResponse.class);
                        GlobalClass global=(GlobalClass)activity.getApplication();

                        int a = 0;
                        for (Sec sector: sectorResponse.getSecs()
                                ) {
                            global.listSectors.add(sector);
                            Log.d("It worked!", sectorResponse.getSecs().get(1).toString());
                            a++;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }
    public void getServicesFromSector(Context t, final UserClientInfo userClientInfo, String idSector, final OnTaskCompleted listener) {
        activity = (Activity) t;
        mListener = listener;
        RequestQueue queue = Volley.newRequestQueue(activity);
        ServiceParams params = new ServiceParams();
        List<Services> listServ = new ArrayList<>();
        listServ.add(new Services(0,null,idSector,0,null));
        params.setUser(userClientInfo);
        params.setList(listServ);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

        // getAllSectors(t, userClientInfo);
        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/service";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reponse:", response);
                        ParserJson<ServicesResult> pj = new ParserJson<>();

                        ServicesResult servicesResult = new ServicesResult();

                        servicesResult = pj.writeObject(response, ServicesResult.class);
                        GlobalClass global=(GlobalClass)activity.getApplication();

                        global.listServices = new ArrayList<Serv>();
                        for (Serv serv: servicesResult.getServ()
                                ) {
                            global.listServices.add(serv);
                        }
                        global.listService.put(global.listServices.get(0).getSector(),global.listServices);
                        listener.onTaskCompleted();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void getOpeningHours(Context t, final UserClientInfo userClientInfo) {
        activity = (Activity) t;

        RequestQueue queue = Volley.newRequestQueue(t);
        ServiceParams params = new ServiceParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/hotelhours";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Opening Hours:", response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void getTransports(Context t, final UserClientInfo userClientInfo) {
        activity = (Activity) t;

        RequestQueue queue = Volley.newRequestQueue(t);
        ServiceParams params = new ServiceParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/transports";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Transports:", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void getTouristicPlaces(Context t, final UserClientInfo userClientInfo) {
        activity = (Activity) t;

        RequestQueue queue = Volley.newRequestQueue(t);
        ServiceParams params = new ServiceParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/touristicplaces";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Touristic places:", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }
    public void getInfoEvent(Context t, final UserClientInfo userClientInfo) {
        activity = (Activity) t;

        RequestQueue queue = Volley.newRequestQueue(t);
        ServiceParams params = new ServiceParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/infoevents";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Info Event:", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }

    public void getMessages(Context t, final UserClientInfo userClientInfo) {
        activity = (Activity) t;

        RequestQueue queue = Volley.newRequestQueue(t);
        ServiceParams params = new ServiceParams();

        params.setUser(userClientInfo);
        GlobalClass global=(GlobalClass)activity.getApplication();
        global.serviceParams=params;
        ParserJson<ServiceParams> pj = new ParserJson<>(params);

        String strJson;
        try {
            strJson = pj.writeJSON();

        } catch (JsonProcessingException e) {
            strJson = "null";
        }
        final String data = strJson;
        String REST_API_URL = "http://212.227.53.116:8080/WSmartgroom/rest/client/message";

        StringRequest myReq = new StringRequest(Request.Method.PUT,
                REST_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Messages :", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", "Error" + error.getLocalizedMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return data.getBytes();
            }

        };
        queue.add(myReq);
    }
}
