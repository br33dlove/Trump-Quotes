package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.InitialisationError;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

public final class InitialiseGameInteractor {
    private final TaskScheduler taskScheduler;
    private final TrumpQuizGameStorageService storageService;
    private final TrumpQuizGameInitialisationService initialisationService;

    public InitialiseGameInteractor(
            TaskScheduler taskScheduler,
            TrumpQuizGameStorageService storageService,
            TrumpQuizGameInitialisationService initialisationService
    ) {
        this.taskScheduler = taskScheduler;
        this.storageService = storageService;
        this.initialisationService = initialisationService;
    }

    public void runTask(final Callback callback) {
        taskScheduler.executeOnWorkerThread(new Task() {
            @Override
            public void execute() {
                tryToGetStoredGameInstance(callback);
            }
        });
    }

    private void tryToGetStoredGameInstance(final Callback callback) {
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

    private void tryToInitialiseNewGameInstance(final Callback callback) {
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

    private void tryToStartGame(final TrumpQuizGame game, final boolean isNewGame, final Callback callback) {
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
            final Callback callback
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
                onSuccess(payload(game, quote, correctAnswers, questionsAnswered, isNewGame), callback);
            }
        });
    }

    private void onSuccess(final Payload payload, final Callback callback) {
        taskScheduler.executeOnMainThread(new Task() {
            @Override
            public void execute() {
                callback.onSuccess(payload);
            }
        });
    }

    private void onError(final Callback callback) {
        taskScheduler.executeOnMainThread(new Task() {
            @Override
            public void execute() {
                callback.onError();
            }
        });
    }

    private Payload payload(
            final TrumpQuizGame game,
            final String quote,
            final int correctAnswers,
            final int questionsAnswered,
            final boolean isNewGame
    ) {
        return new Payload(
                new GetNextQuoteInteractor(game),
                new AnswerIsTrumpInteractor(game),
                new AnswerNotTrumpInteractor(game),
                quote,
                correctAnswers,
                questionsAnswered,
                isNewGame
        );
    }

    public final static class Payload {
        private final GetNextQuoteInteractor getNextQuoteInteractor;
        private final AnswerIsTrumpInteractor answerIsTrumpInteractor;
        private final AnswerNotTrumpInteractor answerNotTrumpInteractor;
        private final String quote;
        private final int correctAnswers;
        private final int questionsAnswered;
        private final boolean isNewGame;

        private Payload(
                GetNextQuoteInteractor getNextQuoteInteractor,
                AnswerIsTrumpInteractor answerIsTrumpInteractor,
                AnswerNotTrumpInteractor answerNotTrumpInteractor,
                String quote,
                int correctAnswers,
                int questionsAnswered,
                boolean isNewGame
        ) {
            this.getNextQuoteInteractor = getNextQuoteInteractor;
            this.answerIsTrumpInteractor = answerIsTrumpInteractor;
            this.answerNotTrumpInteractor = answerNotTrumpInteractor;
            this.quote = quote;
            this.correctAnswers = correctAnswers;
            this.questionsAnswered = questionsAnswered;
            this.isNewGame = isNewGame;
        }
    }

    public interface Callback {
        void onSuccess(final Payload payload);
        void onError();
    }
}
