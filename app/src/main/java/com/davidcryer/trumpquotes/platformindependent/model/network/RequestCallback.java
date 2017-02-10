package com.davidcryer.trumpquotes.platformindependent.model.network;

public interface RequestCallback<ReturnType> {
    void success(final ReturnType quote);
    void failure();
}
