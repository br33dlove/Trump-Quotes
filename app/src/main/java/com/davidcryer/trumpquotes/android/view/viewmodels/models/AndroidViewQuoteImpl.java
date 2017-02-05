package com.davidcryer.trumpquotes.android.view.viewmodels.models;

import android.os.Parcel;

public class AndroidViewQuoteImpl implements AndroidViewQuote {
    private final String id;
    private final String text;

    private AndroidViewQuoteImpl(String id, String text) {
        this.id = id;
        this.text = text;
    }

    private AndroidViewQuoteImpl(final Parcel parcel) {
        id = parcel.readString();
        text = parcel.readString();
    }

    public static AndroidViewQuote newInstance(final String id, final String text) {
        return new AndroidViewQuoteImpl(id, text);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
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
    public String id() {
        return id;
    }

    @Override
    public String text() {
        return text;
    }
}
