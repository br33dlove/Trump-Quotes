package com.davidcryer.trumpquotes.android.view.viewmodels.models;

import android.os.Parcel;

public class AndroidViewQuoteImpl implements AndroidViewQuote {
    private final String text;

    public AndroidViewQuoteImpl(String text) {
        this.text = text;
    }

    private AndroidViewQuoteImpl(final Parcel parcel) {
        text = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
    }

    static final Creator<AndroidViewQuote> CREATOR = new Creator<AndroidViewQuote>() {
        @Override
        public AndroidViewQuote createFromParcel(Parcel source) {
            return new AndroidViewQuoteImpl(source);
        }

        @Override
        public AndroidViewQuote[] newArray(int size) {
            return new AndroidViewQuoteImpl[size];
        }
    };

    @Override
    public String text() {
        return text;
    }
}
