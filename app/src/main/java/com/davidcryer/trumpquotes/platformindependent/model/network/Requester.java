package com.davidcryer.trumpquotes.platformindependent.model.network;

public interface Requester<CallbackType extends RequestCallback> {
    void remove(final CallbackType callback, final boolean cancelRequest);
}
