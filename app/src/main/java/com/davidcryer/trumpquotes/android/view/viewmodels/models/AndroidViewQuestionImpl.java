package com.davidcryer.trumpquotes.android.view.viewmodels.models;

import android.os.Parcel;

public class AndroidViewQuestionImpl implements AndroidViewQuestion {
    private final String id;
    private final String text;

    public AndroidViewQuestionImpl(String id, String text) {
        this.id = id;
        this.text = text;
    }

    private AndroidViewQuestionImpl(final Parcel parcel) {
        id = parcel.readString();
        text = parcel.readString();
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

    static final Creator<AndroidViewQuestion> CREATOR = new Creator<AndroidViewQuestion>() {
        @Override
        public AndroidViewQuestion createFromParcel(Parcel source) {
            return new AndroidViewQuestionImpl(source);
        }

        @Override
        public AndroidViewQuestion[] newArray(int size) {
            return new AndroidViewQuestionImpl[size];
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
