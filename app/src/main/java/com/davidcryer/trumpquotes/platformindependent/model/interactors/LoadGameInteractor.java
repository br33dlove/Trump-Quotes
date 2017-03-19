package com.davidcryer.trumpquotes.platformindependent.model.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domainentities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.services.errors.StorageError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

import java.lang.ref.WeakReference;

public class LoadGameInteractor extends Interactor {
    private final InteractorFactory interactorFactory;
    private final TrumpQuizGameStorageService storageService;

    LoadGameInteractor(TaskScheduler taskScheduler, InteractorFactory interactorFactory, TrumpQuizGameStorageService storageService) {
        super(taskScheduler);
        this.interactorFactory = interactorFactory;
        this.storageService = storageService;
    }

    public void runTask(final WeakReference<Callback> callback) {
        executeOnWorkerThread(new Task() {
            @Override
            public void execute() {
                tryToGetStoredGameInstance(callback);
            }
        });
    }

    private void tryToGetStoredGameInstance(final WeakReference<Callback> callback) {
        storageService.load(new TrumpQuizGameStorageService.LoadCallback() {

            @Override
            public void onLoadGame(TrumpQuizGameImpl game) {
                tryToStartGame(game, callback);
            }

            @Override
            public void onNoSavedGameFound() {
                LoadGameInteractor.this.onNoSavedGameFound(callback);
            }

            @Override
            public void onGameCorrupted() {
                LoadGameInteractor.this.onGameCorrupted(callback);
            }

            @Override
            public void onError(StorageError error) {
                LoadGameInteractor.this.onError(callback);
            }
        });
    }

    private void tryToStartGame(final QuizGame game, final WeakReference<Callback> callback) {
        game.startGame(new QuizGame.StartCallback() {
            @Override
            public void onReturn(int correctAnswers, int questionsAnswered) {
                onLoadGame(new ActiveGameInteractors(interactorFactory, game), correctAnswers, questionsAnswered, callback);
            }
        });
    }

    private void onLoadGame(final ActiveGameInteractors interactors, final int correctAnswers, final int questionsAnswered, final WeakReference<Callback> callback) {
        executeOnMainThread(new Task() {
            @Override
            public void execute() {
                if (callback.get() != null) {
                    callback.get().onLoadGame(interactors, correctAnswers, questionsAnswered);
                }
            }
        });
    }

    private void onNoSavedGameFound(final WeakReference<Callback> callback) {
        executeOnMainThread(new Task() {
            @Override
            public void execute() {
                if (callback.get() != null) {
                    callback.get().onNoSavedGameFound();
                }
            }
        });
    }

    private void onGameCorrupted(final WeakReference<Callback> callback) {
        executeOnMainThread(new Task() {
            @Override
            public void execute() {
                if (callback.get() != null) {
                    callback.get().onGameCorrupted();
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
        void onLoadGame(final ActiveGameInteractors interactors, final int correctAnswers, final int questionsAnswered);
        void onNoSavedGameFound();
        void onGameCorrupted();
        void onError();
    }
}
