package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizAnswer;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

import java.lang.ref.WeakReference;

public final class AnswerQuestionInteractor extends Interactor {
    private final QuizGame game;

    AnswerQuestionInteractor(TaskScheduler taskScheduler, QuizGame game) {
        super(taskScheduler);
        this.game = game;
    }

    public void runTaskAnswerOptionA(final WeakReference<Callback> callback) {
        runTask(QuizAnswer.newInstanceA(), callback);
    }

    public void runTaskAnswerOptionB(final WeakReference<Callback> callback) {
        runTask(QuizAnswer.newInstanceB(), callback);
    }

    private void runTask(final QuizAnswer answer, final WeakReference<Callback> callback) {
        executeOnWorkerThread(new Task() {
            @Override
            public void execute() {
                giveAnswer(answer, callback);
            }
        });
    }

    private void giveAnswer(final QuizAnswer answer, final WeakReference<Callback> callback) {
        game.onAnswerGiven(answer, new QuizGame.AnswerCallback() {
            @Override
            public void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered) {
                executeOnMainThread(new Task() {
                    @Override
                    public void execute() {
                        if (callback.get() != null) {
                            callback.get().onRightAnswerGiven(correctAnswers, questionsAnswered);
                        }
                    }
                });
            }

            @Override
            public void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered) {
                executeOnMainThread(new Task() {
                    @Override
                    public void execute() {
                        if (callback.get() != null) {
                            callback.get().onWrongAnswerGiven(correctAnswers, questionsAnswered);
                        }
                    }
                });
            }
        });
    }

    public interface Callback {
        void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered);
        void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered);
    }
}
