package com.davidcryer.trumpquotes.platformindependent.model.interactors;

import com.davidc.interactor.Interactor;
import com.davidc.interactor.Task;
import com.davidc.interactor.TaskScheduler;
import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizQuestion;

import java.lang.ref.WeakReference;

public final class GetNextQuestionInteractor extends Interactor {
    private final QuizGame game;

    GetNextQuestionInteractor(TaskScheduler taskScheduler, QuizGame game) {
        super(taskScheduler);
        this.game = game;
    }

    public void runTask(final WeakReference<Callback> callback) {
        game.nextQuestion(new QuizGame.NextQuestionCallback() {
            @Override
            public void onGameFinished() {
                executeOnCallbackThread(new Task() {
                    @Override
                    public void execute() {
                        if (callback.get() != null) {
                            callback.get().onGameFinished();
                        }
                    }
                });
            }

            @Override
            public void nextQuestion(final QuizQuestion quizQuestion) {
                executeOnCallbackThread(new Task() {
                    @Override
                    public void execute() {
                        if (callback.get() != null) {
                            callback.get().nextQuestion(quizQuestion);
                        }
                    }
                });
            }
        });
    }

    public interface Callback {
        void nextQuestion(final QuizQuestion quizQuestion);
        void onGameFinished();
    }
}
