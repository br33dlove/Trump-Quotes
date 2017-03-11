package com.davidcryer.trumpquotes.platformindependent.model.domain.services;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;

public interface TrumpQuizGameStorageService {
    void save(TrumpQuizGameImpl game, final SaveCallback callback);
    void load(final LoadCallback callback);

    interface SaveCallback {
        void onSuccess();
        void onError(final StorageError error);
    }

    interface LoadCallback {
        void onLoadGame(final TrumpQuizGameImpl game);
        void onNoSavedGameFound();
        void onGameCorrupted();
        void onError(final StorageError error);
    }
}
