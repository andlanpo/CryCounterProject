package com.example.crycounter;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class BarGraphStressor  {
    private  String stressorName;
    private  int stressorAmount;

    public BarGraphStressor(String stressorName, int stressorAmount) {
        this.stressorName = stressorName;
        this.stressorAmount = stressorAmount;
    }

    public  String getStressorName() {
        return stressorName;
    }

    public  int getStressorAmount() {
        return stressorAmount;
    }


    public  void setStressorName(String stressorName) {
        stressorName = stressorName;
    }

    public  void updateAmount() {
        stressorAmount++;
    }
}