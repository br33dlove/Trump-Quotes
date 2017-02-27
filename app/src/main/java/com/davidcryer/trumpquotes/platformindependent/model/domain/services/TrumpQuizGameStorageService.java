package com.davidcryer.trumpquotes.platformindependent.model.domain.services;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;

public interface TrumpQuizGameStorageService {
    void save(TrumpQuizGameImpl game, final SaveCallback callback);
    void load(final LoadCallback callback);

    interface SaveCallback {
        void onSuccess();
        void onFailure(final StorageError error);
    }

    interface LoadCallback {
        void onSuccess(final TrumpQuizGameImpl game);
        void onFailure(final StorageError error);
    }
}
