package com.davidcryer.trumpquotes.platformindependent.model.network;

public interface Request<CallbackType extends RequestCallback> {
    void cancel();
    boolean isCancelled();
    void add(final CallbackType requestCallback);
    void remove(final CallbackType requestCallback);
    void executeAsync();
}
