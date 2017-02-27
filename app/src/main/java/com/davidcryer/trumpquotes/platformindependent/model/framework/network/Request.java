package com.davidcryer.trumpquotes.platformindependent.model.framework.network;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;

public interface Request extends Cancelable {
    void enqueue();
    boolean isExecuted();
}
