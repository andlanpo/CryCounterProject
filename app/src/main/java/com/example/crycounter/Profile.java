package com.example.crycounter;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Profile implements Parcelable, Comparable<Profile> {

    private boolean moreOrLess;
    private String firstName;
    private String lastName;
//    private int imageResourceID;
    private boolean privacy;
    private ArrayList<String> locations;
    private ArrayList<String> stressors;
    private ArrayList<Cry> cries;
    private String key;

    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[0];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public Profile(Parcel parcel) {
        moreOrLess = parcel.readBoolean();
        firstName = parcel.readString();
        stressors = parcel.readArrayList(null);
        locations = parcel.readArrayList(null);
        cries = parcel.readArrayList(null);
        privacy = parcel.readBoolean();
        lastName = parcel.readString();
        key = parcel.readString();
    }


    public Profile(boolean a, String f, String l, boolean p, ArrayList<String> s, ArrayList<String> lo ){
        moreOrLess = a;
        firstName = f;
        lastName = l;
        //imageResourceID = i;
        stressors = s;
        locations = lo;
        privacy = p;
        cries = new ArrayList<Cry>();
        this.key = "no key yet";
    }

    public Profile() {}

    public Profile(boolean a, String f, String l, boolean p, ArrayList<String> s, ArrayList<String> lo, String key ){
        moreOrLess = a;
        firstName = f;
        lastName = l;
        //imageResourceID = i;
        stressors = s;
        locations = lo;
        privacy = p;
        cries = new ArrayList<Cry>();
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeBoolean(moreOrLess);
        dest.writeBoolean(privacy);
        dest.writeString(lastName);
        dest.writeList(stressors);
        dest.writeList(locations);
        dest.writeList(cries);
        dest.writeString(key);


        //dest.writeInt(imageResourceID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setMoreOrLess(boolean moreOrLess) {
        this.moreOrLess = moreOrLess;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //public void setImageResourceID(int imageResourceID) {
        //this.imageResourceID = imageResourceID;
    //}

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    //public int getImageResourceID() {
        //return imageResourceID;
    //}

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isMoreOrLess() {
        return moreOrLess;
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

    @Override
    public int compareTo(Profile other) {
        if(this.getFirstName().equals(other.getFirstName())){
            if(this.getLastName().equals(other.getLastName())){
                return 0;
            }
        }
        else{
            return -1;
        }
        return 0;
    }
}