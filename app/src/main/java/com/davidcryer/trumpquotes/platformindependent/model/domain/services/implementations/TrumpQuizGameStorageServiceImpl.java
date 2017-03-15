package com.davidcryer.trumpquotes.platformindependent.model.domain.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.DomainModelMapper;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;
import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;
import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizQuestionStorageModel;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizQuestionStore;

class TrumpQuizGameStorageServiceImpl implements TrumpQuizGameStorageService {
    private final TrumpQuizGameStore gameStore;
    private final TrumpQuizQuestionStore questionStore;

    TrumpQuizGameStorageServiceImpl(TrumpQuizGameStore gameStore, TrumpQuizQuestionStore questionStore) {
        this.gameStore = gameStore;
        this.questionStore = questionStore;
    }

    @Override
    public void save(TrumpQuizGameImpl game, SaveCallback callback) {
        gameStore.clear();
        questionStore.clearAll();
        final boolean savedGame = savedGame(game);
        if (savedGame) {
            callback.onSuccess();
        } else {
            callback.onError(new StorageError());
        }
    }

    private boolean savedGame(final TrumpQuizGameImpl game) {
        final boolean storedGame = gameStore.store(DomainModelMapper.mapGame(game));
        if (storedGame) {
            final boolean storedQuestions = questionStore.store(DomainModelMapper.mapQuestions(game));
            if (storedQuestions) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void load(LoadCallback callback) {
        final TrumpQuizGameImpl loadedGame = loadedGame();
        if (loadedGame != null) {
            callback.onLoadGame(loadedGame);
        } else {
            callback.onError(new StorageError());
        }
    }

    private TrumpQuizGameImpl loadedGame() {
        final TrumpQuizGameStorageModel gameStorageModel = gameStore.retrieve();
        if (gameStorageModel != null) {
            final TrumpQuizQuestionStorageModel[] questionStorageModels = questionStore.retrieve(gameStorageModel.questionIds);
            if (questionStorageModels != null) {
                return DomainModelMapper.mapStorageModels(gameStorageModel, questionStorageModels);
            }
        }
        return null;
    }
}
