package com.davidcryer.trumpquotes.android.model.framework.network;

import com.davidcryer.trumpquotes.android.model.framework.store.Cancelable;

public interface Request extends Cancelable {
    void enqueue();
    boolean isExecuted();
}
