package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.InitialisationError;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

import java.lang.ref.WeakReference;

public final class InitialiseGameInteractor extends Interactor {
    private final static int QUESTION_COUNT = 10;
    private final InteractorFactory interactorFactory;
    private final TrumpQuizGameInitialisationService initialisationService;

    InitialiseGameInteractor(
            TaskScheduler taskScheduler,
            InteractorFactory interactorFactory,
            TrumpQuizGameInitialisationService initialisationService
    ) {
        super(taskScheduler);
        this.interactorFactory = interactorFactory;
        this.initialisationService = initialisationService;
    }

    public void runTask(final WeakReference<Callback> callback) {
        executeOnWorkerThread(new Task() {
            @Override
            public void execute() {
                tryToInitialiseNewGameInstance(callback);
            }
        });
    }

    private void tryToInitialiseNewGameInstance(final WeakReference<Callback> callback) {
        initialisationService.initialiseNewGame(QUESTION_COUNT, new TrumpQuizGameInitialisationService.Callback() {
            @Override
            public void onSuccess(QuizGame game) {
                tryToStartGame(game, callback);
            }

            @Override
            public void onFailure(InitialisationError error) {
                onError(callback);
            }
        });
    }

    private void tryToStartGame(final QuizGame game, final WeakReference<Callback> callback) {
        game.startGame(new QuizGame.StartCallback() {
            @Override
            public void onReturn(int correctAnswers, int questionsAnswered) {
                onSuccess(new ActiveGameInteractors(interactorFactory, game), correctAnswers, questionsAnswered, callback);
            }
        });
    }

    private void onSuccess(final ActiveGameInteractors interactors, final int correctAnswers, final int questionsAnswered, final WeakReference<Callback> callback) {
        executeOnMainThread(new Task() {
            @Override
            public void execute() {
                if (callback.get() != null) {
                    callback.get().onInitialiseGame(interactors, correctAnswers, questionsAnswered);
                }
            }
        });
    }

    private void onError(final WeakReference<Callback> callback) {
        executeOnMainThread(new Task() {
            @Override
            public void execute() {
                if (callback.get() != null) {
                    callback.get().onError();
                }
            }
        });
    }

    public interface Callback {
        void onInitialiseGame(final ActiveGameInteractors interactors, final int correctAnswers, final int questionsAnswered);
        void onError();
    }
}
