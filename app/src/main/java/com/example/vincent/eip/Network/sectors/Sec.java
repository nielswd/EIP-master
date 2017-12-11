
package com.example.vincent.eip.Network.sectors;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sec implements Serializable, Parcelable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("idSector")
    @Expose
    private Integer idSector;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Sec> CREATOR = new Creator<Sec>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Sec createFromParcel(Parcel in) {
            Sec instance = new Sec();
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.idSector = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Sec[] newArray(int size) {
            return (new Sec[size]);
        }

    }
    ;
    private final static long serialVersionUID = 9055453069114885846L;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sec withDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public Sec withIdSector(Integer idSector) {
        this.idSector = idSector;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sec withName(String name) {
        this.name = name;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(idSector);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
