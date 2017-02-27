package com.davidcryer.trumpquotes.platformindependent.model.domain.tasks;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.InitialisationError;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;

public final class InitialiseGameTask extends Task<Void, InitialiseGameTask.ResponseValues> {
    private final TrumpQuizGameStorageService storageService;
    private final TrumpQuizGameInitialisationService initialisationService;

    public InitialiseGameTask(
            TrumpQuizGameStorageService storageService,
            TrumpQuizGameInitialisationService initialisationService
    ) {
        this.storageService = storageService;
        this.initialisationService = initialisationService;
    }

    @Override
    protected void doTask(Void requestValues, Callback<ResponseValues> callback) {
        tryToGetStoredGameInstance(callback);
    }

    private void tryToGetStoredGameInstance(final Callback<ResponseValues> callback) {
        storageService.load(new TrumpQuizGameStorageService.LoadCallback() {

            @Override
            public void onSuccess(TrumpQuizGameImpl game) {
                tryToStartGame(game, false, callback);
            }

            @Override
            public void onFailure(StorageError error) {
                tryToInitialiseNewGameInstance(callback);
            }
        });
    }

    private void tryToInitialiseNewGameInstance(final Callback<ResponseValues> callback) {
        initialisationService.initialiseNewGame(10, new TrumpQuizGameInitialisationService.Callback() {//TODO replace hardcoded int
            @Override
            public void onSuccess(TrumpQuizGame game) {
                tryToStartGame(game, true, callback);
            }

            @Override
            public void onFailure(InitialisationError error) {
                onError(callback);
            }
        });
    }

    private void tryToStartGame(final TrumpQuizGame game, final boolean isNewGame, final Callback<ResponseValues> callback) {
        game.startGame(new TrumpQuizGame.StartCallback() {
            @Override
            public void onReturn(int correctAnswers, int questionsAnswered) {
                tryToGetNextQuote(game, correctAnswers, questionsAnswered, isNewGame, callback);
            }
        });
    }

    private void tryToGetNextQuote(
            final TrumpQuizGame game,
            final int correctAnswers,
            final int questionsAnswered,
            final boolean isNewGame,
            final Callback<ResponseValues> callback
    ) {
        game.nextQuote(new TrumpQuizGame.NextQuoteCallback() {
            @Override
            public void onGameFinished() {
                if (isNewGame) {
                    onError(callback);
                } else {
                    tryToInitialiseNewGameInstance(callback);
                }
            }

            @Override
            public void onNextQuote(String quote) {
                InitialiseGameTask.this.onSuccess(
                        responseValues(game, quote, correctAnswers, questionsAnswered, isNewGame),
                        callback
                );
            }
        });
    }

    private ResponseValues responseValues(
            final TrumpQuizGame game,
            final String quote,
            final int correctAnswers,
            final int questionsAnswered,
            final boolean isNewGame
    ) {
        return new ResponseValues(
                new GetNextQuoteTask(game),
                new AnswerIsTrumpTask(game),
                new AnswerNotTrumpTask(game),
                quote,
                correctAnswers,
                questionsAnswered,
                isNewGame
        );
    }

    public final static class ResponseValues {
        private final GetNextQuoteTask getNextQuoteTask;
        private final AnswerIsTrumpTask answerIsTrumpTask;
        private final AnswerNotTrumpTask answerNotTrumpTask;
        private final String quote;
        private final int correctAnswers;
        private final int questionsAnswered;
        private final boolean isNewGame;

        private ResponseValues(
                GetNextQuoteTask getNextQuoteTask,
                AnswerIsTrumpTask answerIsTrumpTask,
                AnswerNotTrumpTask answerNotTrumpTask,
                String quote,
                int correctAnswers,
                int questionsAnswered,
                boolean isNewGame
        ) {
            this.getNextQuoteTask = getNextQuoteTask;
            this.answerIsTrumpTask = answerIsTrumpTask;
            this.answerNotTrumpTask = answerNotTrumpTask;
            this.quote = quote;
            this.correctAnswers = correctAnswers;
            this.questionsAnswered = questionsAnswered;
            this.isNewGame = isNewGame;
        }
    }
}
