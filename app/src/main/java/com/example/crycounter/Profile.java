package com.example.crycounter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {

    private boolean moreOrLess;
    private String firstName;
    private String lastName;
    private int imageResourceID;
    private boolean privacy;


    public Profile(boolean a, String f, String l, int i, boolean p){
        moreOrLess = a;
        firstName = f;
        lastName = l;
        imageResourceID = i;
        privacy = p;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public Profile(Parcel parcel){
        moreOrLess = parcel.readBoolean();
        firstName = parcel.readString();
        lastName = parcel.readString();
        imageResourceID = parcel.readInt();
        privacy = parcel.readBoolean();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeBoolean(moreOrLess);
        dest.writeBoolean(privacy);
        dest.writeString(lastName);
        dest.writeInt(imageResourceID);
    }

    public static final Parcelable.Creator<Profile> CREATOR = new
            Parcelable.Creator<Profile>() {

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

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isMoreOrLess() {
        return moreOrLess;
    }
}