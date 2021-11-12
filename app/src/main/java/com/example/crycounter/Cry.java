package com.example.crycounter;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Cry implements Parcelable{

    private String location;
    private String stressor;
    private int hour;
    private int minute;
    private int dayOfWeek;
    private int year;
    private int dayOfMonth;
    private int month;

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

    public Cry(int a, int b, int c, int d, int e, int f, String l, String s){
        hour = a;
        minute = b;
        dayOfWeek = c;
        dayOfMonth = d;
        month = e;
        year = f;
        location = l;
        stressor = s;
    }

    protected Cry(Parcel in) {
        this.location = in.readString();
        this.stressor = in.readString();
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.dayOfWeek = in.readInt();
        this.year = in.readInt();
        this.dayOfMonth = in.readInt();
        this.month = in.readInt();
    }

    public Cry() {
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.location);
        dest.writeString(this.stressor);
        dest.writeInt(this.hour);
        dest.writeInt(this.minute);
        dest.writeInt(this.dayOfWeek);
        dest.writeInt(this.year);
        dest.writeInt(this.dayOfMonth);
        dest.writeInt(this.month);
    }

    public void readFromParcel(Parcel source) {
        this.location = source.readString();
        this.stressor = source.readString();
        this.hour = source.readInt();
        this.minute = source.readInt();
        this.dayOfWeek = source.readInt();
        this.year = source.readInt();
        this.dayOfMonth = source.readInt();
        this.month = source.readInt();
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
