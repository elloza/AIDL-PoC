package com.lozasolutions.namesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class NameQuote implements Parcelable {

    private String quote;

    public NameQuote(Parcel source) {
        quote = source.readString();
    }

    public NameQuote(String quote) {
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
