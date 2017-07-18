package com.lozasolutions.bankapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class BankResult implements Parcelable {

    private String mPath;

    public BankResult(Parcel source) {
        mPath = source.readString();
    }

    public BankResult(String path) {
        mPath = path;
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPath);
    }

    public static final Parcelable.Creator<BankResult> CREATOR = new Parcelable.Creator<BankResult>() {
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
