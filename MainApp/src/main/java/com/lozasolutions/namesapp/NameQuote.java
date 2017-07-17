package com.lozasolutions.namesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Aidan Follestad (lozasolutions)
 */
public class NameQuote implements Parcelable {

    private String mPath;

    public NameQuote(Parcel source) {
        mPath = source.readString();
    }

    public NameQuote(String path) {
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

    public static final Creator<NameQuote> CREATOR = new Creator<NameQuote>() {
        @Override
        public NameQuote[] newArray(int size) {
            return new NameQuote[size];
        }

        @Override
        public NameQuote createFromParcel(Parcel source) {
            return new NameQuote(source);
        }
    };
}
