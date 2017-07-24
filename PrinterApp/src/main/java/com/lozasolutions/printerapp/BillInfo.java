package com.lozasolutions.printerapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class BillInfo implements Parcelable {

    private String quote;

    public BillInfo(Parcel source) {
        quote = source.readString();
    }

    public BillInfo(String quote) {
        quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quote);
    }

    public static final Creator<BillInfo> CREATOR = new Creator<BillInfo>() {
        @Override
        public BillInfo[] newArray(int size) {
            return new BillInfo[size];
        }

        @Override
        public BillInfo createFromParcel(Parcel source) {
            return new BillInfo(source);
        }
    };
}

