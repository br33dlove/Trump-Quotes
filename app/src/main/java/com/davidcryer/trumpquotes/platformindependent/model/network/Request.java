package com.davidcryer.trumpquotes.platformindependent.model.network;

public interface Request<CallbackType extends RequestCallback> {
    void cancel();
    boolean isCancelled();
    void executeAsync();
    boolean isExecuted();
}
