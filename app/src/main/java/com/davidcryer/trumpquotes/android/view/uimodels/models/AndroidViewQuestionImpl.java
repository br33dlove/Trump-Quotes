package com.davidcryer.trumpquotes.android.view.uimodels.models;

import android.os.Parcel;

class AndroidViewQuestionImpl implements AndroidViewQuestion {
    private final String quote;
    private final String optionA;
    private final String optionB;

    AndroidViewQuestionImpl(String quote, String optionA, String optionB) {
        this.quote = quote;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    private AndroidViewQuestionImpl(final Parcel parcel) {
        quote = parcel.readString();
        optionA = parcel.readString();
        optionB = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quote);
        dest.writeString(optionA);
        dest.writeString(optionB);
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
    public String quote() {
        return quote;
    }

    @Override
    public String optionA() {
        return optionA;
    }

    @Override
    public String optionB() {
        return optionB;
    }
}
