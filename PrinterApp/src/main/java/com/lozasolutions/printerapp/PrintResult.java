package com.lozasolutions.printerapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class PrintResult implements Parcelable {

    private String quote;

    public PrintResult(Parcel source) {
        quote = source.readString();
    }

    public PrintResult(String quote) {
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

    public static final Creator<PrintResult> CREATOR = new Creator<PrintResult>() {
        @Override
        public PrintResult[] newArray(int size) {
            return new PrintResult[size];
        }

        @Override
        public PrintResult createFromParcel(Parcel source) {
            return new PrintResult(source);
        }
    };
}

