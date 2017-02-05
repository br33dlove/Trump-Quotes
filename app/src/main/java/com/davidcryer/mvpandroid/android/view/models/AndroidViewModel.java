package com.davidcryer.mvpandroid.android.view.models;

import android.os.Parcelable;

import com.davidcryer.mvpandroid.android.view.ui.AndroidMvpView;

interface AndroidViewModel<ViewType extends AndroidMvpView> extends Parcelable {
    void onto(final ViewType view);
}
