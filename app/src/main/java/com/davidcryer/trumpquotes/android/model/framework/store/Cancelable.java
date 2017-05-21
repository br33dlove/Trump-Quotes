package com.davidcryer.trumpquotes.android.model.framework.store;

public interface Cancelable {
    void cancel();
    boolean isCancelled();
}
