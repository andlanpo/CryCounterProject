package com.example.crycounter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Profile implements Parcelable{

    private boolean moreOrLess;
    private String firstName;
    private String lastName;
    private boolean privacy;
    private ArrayList<String> locations;
    private ArrayList<String> stressors;
    private ArrayList<Cry> cries;
    private String UID;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.moreOrLess ? (byte) 1 : (byte) 0);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeByte(this.privacy ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.locations);
        dest.writeStringList(this.stressors);
        dest.writeTypedList(this.cries);
        dest.writeString(this.UID);
    }

    public void readFromParcel(Parcel source) {
        this.moreOrLess = source.readByte() != 0;
        this.firstName = source.readString();
        this.lastName = source.readString();
        this.privacy = source.readByte() != 0;
        this.locations = source.createStringArrayList();
        this.stressors = source.createStringArrayList();
        this.cries = source.createTypedArrayList(Cry.CREATOR);
        this.UID = source.readString();
    }

    public Profile() {
    }

    protected Profile(Parcel in) {
        this.moreOrLess = in.readByte() != 0;
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.privacy = in.readByte() != 0;
        this.locations = in.createStringArrayList();
        this.stressors = in.createStringArrayList();
        this.cries = in.createTypedArrayList(Cry.CREATOR);
        this.UID = in.readString();
    }

    public Profile(boolean a, String f, String l, boolean p, ArrayList<String> s, ArrayList<String> lo, String uid ){
        firstName = f;
        moreOrLess = a;
        privacy = p;
        lastName = l;
        stressors = s;
        locations = lo;
        cries = new ArrayList<Cry>();
        this.UID = uid;
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public boolean isMoreOrLess() {
        return moreOrLess;
    }

    public void setMoreOrLess(boolean moreOrLess) {
        this.moreOrLess = moreOrLess;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<String> getStressors() {
        return stressors;
    }

    public void setStressors(ArrayList<String> stressors) {
        this.stressors = stressors;
    }

    public ArrayList<Cry> getCries() {
        return cries;
    }

    public void setCries(ArrayList<Cry> cries) {
        this.cries = cries;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
