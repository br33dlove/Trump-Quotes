package com.davidcryer.trumpquotes.platformindependent.model.framework;

public interface Cancelable {
    void cancel();
    boolean isCancelled();
}
