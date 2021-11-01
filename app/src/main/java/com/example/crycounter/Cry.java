package com.example.crycounter;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Cry implements Parcelable{

    private String location;
    private String stressor;
    private int date;
    private int time;


    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.location);
        dest.writeString(this.stressor);
        dest.writeInt(this.date);
        dest.writeInt(this.time);
    }

    public void readFromParcel(Parcel source) {
        this.location = source.readString();
        this.stressor = source.readString();
        this.date = source.readInt();
        this.time = source.readInt();
    }

    public Cry() {
    }

    protected Cry(Parcel in) {
        this.location = in.readString();
        this.stressor = in.readString();
        this.date = in.readInt();
        this.time = in.readInt();
    }

    public static final Creator<Cry> CREATOR = new Creator<Cry>() {
        @Override
        public Cry createFromParcel(Parcel source) {
            return new Cry(source);
        }

        @Override
        public Cry[] newArray(int size) {
            return new Cry[size];
        }
    };

    public Cry(int a, String f, String l, int p){
        date = a;
        stressor = f;
        location = l;
        time = p;
    }
}
