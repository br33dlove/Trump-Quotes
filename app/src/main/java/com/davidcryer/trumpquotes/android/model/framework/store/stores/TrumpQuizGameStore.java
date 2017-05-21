package com.davidcryer.trumpquotes.android.model.framework.store.stores;

import com.davidcryer.trumpquotes.android.model.framework.store.models.TrumpQuizGameStorageModel;

public interface TrumpQuizGameStore {
    boolean store(final TrumpQuizGameStorageModel model);
    boolean clear();
    TrumpQuizGameStorageModel retrieve();
}
