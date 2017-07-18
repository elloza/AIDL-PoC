package com.lozasolutions.bankapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class BankResult implements Parcelable {

    private String jsonResponse;

    public BankResult(Parcel source) {
        jsonResponse = source.readString();
    }

    public BankResult(String jsonResponse) {
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

    public static final Creator<BankResult> CREATOR = new Creator<BankResult>() {
        @Override
        public BankResult[] newArray(int size) {
            return new BankResult[size];
        }

        @Override
        public BankResult createFromParcel(Parcel source) {
            return new BankResult(source);
        }
    };
}
