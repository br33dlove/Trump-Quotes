package com.davidcryer.trumpquotes.platformindependent.model.store.stores;

import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;

public interface TrumpQuizGameStore {
    boolean store(final TrumpQuizGameStorageModel model);
    boolean clear();
    TrumpQuizGameStorageModel retrieve();
}
