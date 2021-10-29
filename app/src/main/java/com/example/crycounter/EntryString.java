package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

import com.github.mikephil.charting.data.BaseEntry;
import com.github.mikephil.charting.utils.Utils;

public class EntryString extends BaseEntry implements Parcelable {

        /**
         * the x value
         */
        private String x = "";

        public EntryString() {

        }

        /**
         * A Entry represents one single entry in the chart.
         *
         * @param x the x value
         * @param y the y value (the actual value of the entry)
         */
        public EntryString(String x, float y) {
            super(y);
            this.x = x;
        }

        /**
         * A Entry represents one single entry in the chart.
         *
         * @param x    the x value
         * @param y    the y value (the actual value of the entry)
         * @param data Spot for additional data this Entry represents.
         */
        public EntryString(String x, float y, Object data) {
            super(y, data);
            this.x = x;
        }

        /**
         * A EntryString represents one single entry in the chart.
         *
         * @param x    the x value
         * @param y    the y value (the actual value of the entry)
         * @param icon icon image
         */
        public EntryString(String x, float y, Drawable icon) {
            super(y, icon);
            this.x = x;
        }

        /**
         * A Entry represents one single entry in the chart.
         *
         * @param x    the x value
         * @param y    the y value (the actual value of the entry)
         * @param icon icon image
         * @param data Spot for additional data this Entry represents.
         */
        public EntryString(String x, float y, Drawable icon, Object data) {
            super(y, icon, data);
            this.x = x;
        }

        /**
         * Returns the x-value of this Entry object.
         *
         * @return
         */
        public String getX() {
            return x;
        }

        /**
         * Sets the x-value of this Entry object.
         *
         * @param x
         */
        public void setX(String x) {
            this.x = x;
        }

        /**
         * returns an exact copy of the entry
         *
         * @return
         */
        public EntryString copy() {
            EntryString e = new EntryString(x, getY(), getData());
            return e;
        }

        /**
         * Compares value, xIndex and data of the entries. Returns true if entries
         * are equal in those points, false if not. Does not check by hash-code like
         * it's done by the "equals" method.
         *
         * @param e
         * @return
         */
        public boolean equalTo(EntryString e) {

            if (e == null)
                return false;

            if (e.getData() != this.getData())
                return false;


            if (Math.abs(e.getY() - this.getY()) > Utils.FLOAT_EPSILON)
                return false;

            return true;
        }

        /**
         * returns a string representation of the entry containing x-index and value
         */
        @Override
        public String toString() {
            return "Entry, x: " + x + " y: " + getY();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.x);
            dest.writeFloat(this.getY());
            if (getData() != null) {
                if (getData() instanceof Parcelable) {
                    dest.writeInt(1);
                    dest.writeParcelable((Parcelable) this.getData(), flags);
                } else {
                    throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
                }
            } else {
                dest.writeInt(0);
            }
        }

        protected EntryString(Parcel in) {
            this.x = in.readString();
            this.setY(in.readFloat());
            if (in.readInt() == 1) {
                this.setData(in.readParcelable(Object.class.getClassLoader()));
            }
        }

        public static final Parcelable.Creator<EntryString> CREATOR = new Parcelable.Creator<EntryString>() {
            public EntryString createFromParcel(Parcel source) {
                return new EntryString(source);
            }

            public EntryString[] newArray(int size) {
                return new EntryString[size];
            }
        };
    }

