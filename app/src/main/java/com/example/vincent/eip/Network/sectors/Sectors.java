
package com.example.vincent.eip.Network.sectors;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sectors implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("secs")
    @Expose
    private List<Sec> secs = null;
    public final static Creator<Sectors> CREATOR = new Creator<Sectors>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Sectors createFromParcel(Parcel in) {
            Sectors instance = new Sectors();
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.secs, (Sec.class.getClassLoader()));
            return instance;
        }

        public Sectors[] newArray(int size) {
            return (new Sectors[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7102476170974066337L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Sectors withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Sec> getSecs() {
        return secs;
    }

    public void setSecs(List<Sec> secs) {
        this.secs = secs;
    }

    public Sectors withSecs(List<Sec> secs) {
        this.secs = secs;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeList(secs);
    }

    public int describeContents() {
        return  0;
    }

}
