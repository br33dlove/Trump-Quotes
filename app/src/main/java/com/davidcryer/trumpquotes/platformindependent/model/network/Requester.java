package com.davidcryer.trumpquotes.platformindependent.model.network;

public interface Requester<CallbackType extends RequestCallback> {
    void release(final CallbackType callback);
    void cancelRequests();
}
