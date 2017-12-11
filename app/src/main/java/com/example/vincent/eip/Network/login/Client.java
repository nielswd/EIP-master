
package com.example.vincent.eip.Network.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client implements Parcelable
{

    @SerializedName("checkInDate")
    @Expose
    private String checkInDate;
    @SerializedName("checkOutDate")
    @Expose
    private String checkOutDate;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("fk_idRoom")
    @Expose
    private Integer fkIdRoom;
    @SerializedName("idClient")
    @Expose
    private Integer idClient;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("pwd")
    @Expose
    private String pwd;
    public final static Creator<Client> CREATOR = new Creator<Client>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        public Client[] newArray(int size) {
            return (new Client[size]);
        }

    }
    ;

    protected Client(Parcel in) {
        this.checkInDate = ((String) in.readValue((String.class.getClassLoader())));
        this.checkOutDate = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.fkIdRoom = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idClient = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.login = ((String) in.readValue((String.class.getClassLoader())));
        this.pwd = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Client() {
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getFkIdRoom() {
        return fkIdRoom;
    }

    public void setFkIdRoom(Integer fkIdRoom) {
        this.fkIdRoom = fkIdRoom;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(checkInDate);
        dest.writeValue(checkOutDate);
        dest.writeValue(firstName);
        dest.writeValue(fkIdRoom);
        dest.writeValue(idClient);
        dest.writeValue(lastName);
        dest.writeValue(login);
        dest.writeValue(pwd);
    }

    public int describeContents() {
        return  0;
    }

}
