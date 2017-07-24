package com.lozasolutions.bankapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class BankInfo implements Parcelable {

    private String jsonResponse;

    public BankInfo(Parcel source) {
        jsonResponse = source.readString();
    }

    public BankInfo(String jsonResponse) {
        jsonResponse = jsonResponse;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jsonResponse);
    }

    public static final Creator<BankInfo> CREATOR = new Creator<BankInfo>() {
        @Override
        public BankInfo[] newArray(int size) {
            return new BankInfo[size];
        }

        @Override
        public BankInfo createFromParcel(Parcel source) {
            return new BankInfo(source);
        }
    };
}
