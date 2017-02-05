package com.davidcryer.trumpquotes.android.view.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.davidcryer.trumpquotes.android.view.ui.TemplateAndroidView;

public class TemplateViewModel implements TemplateAndroidViewModel {
    private boolean screenChanged;

    private TemplateViewModel(final boolean screenChanged) {
        this.screenChanged = screenChanged;
    }

    private TemplateViewModel(final Parcel parcel) {
        screenChanged = parcel.readByte() != 0;
    }

    public static TemplateViewModel newInstance(final boolean screenChanged) {
        return new TemplateViewModel(screenChanged);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (screenChanged ? 1 : 0));
    }

    final static Parcelable.Creator<TemplateViewModel> CREATOR = new Parcelable.Creator<TemplateViewModel>() {
        @Override
        public TemplateViewModel createFromParcel(Parcel source) {
            return new TemplateViewModel(source);
        }

        @Override
        public TemplateViewModel[] newArray(int size) {
            return new TemplateViewModel[size];
        }
    };

    @Override
    public void screenChanged(TemplateAndroidView view) {
        screenChanged = true;
        if (view != null) {
            view.someScreenChange();
        }
    }

    @Override
    public void onto(TemplateAndroidView view) {
        if (screenChanged) {
            view.someScreenChange();
        }
    }
}
