package com.davidcryer.trumpquotes.platformindependent.model.network;

public interface Request<CallbackType extends RequestCallback> {
    boolean hasResponse();
    void cancel();
    boolean isCancelled();
    void remove(final CallbackType requestCallback);
}
