package com.davidcryer.trumpquotes.android.view.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;

public class QuotesViewModel implements QuotesAndroidViewModel {
    private boolean screenChanged;

    private QuotesViewModel(final boolean screenChanged) {
        this.screenChanged = screenChanged;
    }

    private QuotesViewModel(final Parcel parcel) {
        screenChanged = parcel.readByte() != 0;
    }

    public static QuotesViewModel newInstance(final boolean screenChanged) {
        return new QuotesViewModel(screenChanged);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (screenChanged ? 1 : 0));
    }

    final static Parcelable.Creator<QuotesViewModel> CREATOR = new Parcelable.Creator<QuotesViewModel>() {
        @Override
        public QuotesViewModel createFromParcel(Parcel source) {
            return new QuotesViewModel(source);
        }

        @Override
        public QuotesViewModel[] newArray(int size) {
            return new QuotesViewModel[size];
        }
    };

    @Override
    public void screenChanged(QuotesAndroidView view) {
        screenChanged = true;
        if (view != null) {
            view.someScreenChange();
        }
    }

    @Override
    public void onto(QuotesAndroidView view) {
        if (screenChanged) {
            view.someScreenChange();
        }
    }
}
