package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

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
                executeOnMainThread(new Task() {
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
                executeOnMainThread(new Task() {
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
