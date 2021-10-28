package com.example.crycounter;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Cry implements Parcelable, Comparable<Cry>{

    private String location;
    private String stressor;
    private int date;
    private int time;

    public static final Parcelable.Creator<Cry> CREATOR = new Parcelable.Creator<Cry>() {

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Cry createFromParcel(Parcel parcel) {
            return new Cry(parcel);
        }

        @Override
        public Cry[] newArray(int size) {
            return new Cry[0];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public Cry(Parcel parcel) {
        stressor = parcel.readString();
        date = parcel.readInt();
        time = parcel.readInt();
        location = parcel.readString();
    }


    public Cry(int a, String f, String l, int p){
        date = a;
        stressor = f;
        location = l;
        time = p;
    }
    public Cry(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStressor() {
        return stressor;
    }

    public void setStressor(String stressor) {
        this.stressor = stressor;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeInt(date);
        dest.writeInt(time);
        dest.writeString(stressor);
        //dest.writeInt(imageResourceID);
    }

    public int describeContents() {
        return 0;
    }

    public int compareTo(Cry other) {
        if(this.getStressor() == other.getStressor()){
            if(this.getLocation() == other.getLocation()){
                return 0;
            }
        }
        else{
            return -1;
        }
        return 0;
    }
}
