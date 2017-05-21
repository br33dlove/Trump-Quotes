package com.davidcryer.trumpquotes.android.model.framework.network;

public interface RequestCallback<ReturnType> {
    void success(final ReturnType quote);
    void failure();
}
