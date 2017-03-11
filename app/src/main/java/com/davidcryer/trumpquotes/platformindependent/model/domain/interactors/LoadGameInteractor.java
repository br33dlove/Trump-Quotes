package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.StorageError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

import java.lang.ref.WeakReference;

public class LoadGameInteractor extends Interactor {
    private final InteractorFactory interactorFactory;
    private final TrumpQuizGameStorageService storageService;

    public LoadGameInteractor(TaskScheduler taskScheduler, InteractorFactory interactorFactory, TrumpQuizGameStorageService storageService) {
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
            public void onSuccess(TrumpQuizGameImpl game) {
                tryToStartGame(game, false, callback);
            }

            @Override
            public void onFailure(StorageError error) {

            }
        });
    }

    private void tryToStartGame(final QuizGame game, final boolean isNewGame, final WeakReference<Callback> callback) {
        game.startGame(new QuizGame.StartCallback() {
            @Override
            public void onReturn(int correctAnswers, int questionsAnswered) {
                tryToGetNextQuote(game, correctAnswers, questionsAnswered, isNewGame, callback);
            }
        });
    }

    private void tryToGetNextQuote(
            final QuizGame game,
            final int correctAnswers,
            final int questionsAnswered,
            final WeakReference<Callback> callback
    ) {
        game.nextQuestion(new QuizGame.NextQuestionCallback() {
            @Override
            public void onGameFinished() {
                tryToInitialiseNewGameInstance(callback);
            }

            @Override
            public void nextQuestion(QuizQuestion quizQuestion) {
                onSuccess(payload(game, quizQuestion, correctAnswers, questionsAnswered, isNewGame), callback);
            }
        });
    }

    private void onLoadGame() {

    }

    private void onNoSavedGameFound() {

    }

    private void onGameCorrupted() {

    }

    private void onError() {

    }

    private Payload payload(
            final QuizGame game,
            final int correctAnswers,
            final int questionsAnswered
    ) {
        return new Payload(
                new ActiveGameInteractors(
                        interactorFactory.createAnswerNotTrumpInteractor(game),
                        interactorFactory.createGetNextQuoteInteractor(game)
                ),
                correctAnswers,
                questionsAnswered
        );
    }

    public final static class Payload {
        public final ActiveGameInteractors activeGameInteractors;
        public final int correctAnswers;
        public final int questionsAnswered;

        private Payload(
                ActiveGameInteractors activeGameInteractors,
                int correctAnswers,
                int questionsAnswered
        ) {
            this.activeGameInteractors = activeGameInteractors;
            this.correctAnswers = correctAnswers;
            this.questionsAnswered = questionsAnswered;
        }
    }

    public interface Callback {
        void onLoadGame(final Payload payload);
        void onNoSavedGameFound();
        void onGameCorrupted();
        void onError();
    }
}
