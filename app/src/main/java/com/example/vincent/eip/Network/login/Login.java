
package com.example.vincent.eip.Network.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login implements Parcelable
{

    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    public final static Creator<Login> CREATOR = new Creator<Login>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    }
    ;

    protected Login(Parcel in) {
        this.client = ((Client) in.readValue((Client.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.sessionId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Login() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(client);
        dest.writeValue(code);
        dest.writeValue(sessionId);
    }

    public int describeContents() {
        return  0;
    }

}
