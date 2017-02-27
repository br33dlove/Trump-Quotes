package com.davidcryer.trumpquotes.platformindependent.model.domain.services;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.InitialisationError;

public interface TrumpQuizGameInitialisationService {
    void initialiseNewGame(final int questionCount, final Callback callback);

    interface Callback {
        void onSuccess(final TrumpQuizGame game);
        void onFailure(final InitialisationError error);
    }
}
