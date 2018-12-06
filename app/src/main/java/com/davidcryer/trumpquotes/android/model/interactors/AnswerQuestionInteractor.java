package com.davidcryer.trumpquotes.android.model.interactors;

import com.davidc.interactor.Interactor;
import com.davidc.interactor.Scheduler;
import com.davidcryer.trumpquotes.android.model.domainentities.QuizAnswer;
import com.davidcryer.trumpquotes.android.model.tasks.AnswerQuestionTask;
import com.davidcryer.trumpquotes.android.model.tasks.AnswerQuestionTaskCallback;

import java.lang.ref.WeakReference;

public final class AnswerQuestionInteractor extends Interactor {
    private final AnswerQuestionTask task;

    AnswerQuestionInteractor(Scheduler scheduler, AnswerQuestionTask task) {
        super(scheduler);
        this.task = task;
    }

    public void answer(final QuizAnswer answer, final WeakReference<AnswerQuestionTaskCallback> callback) {
        runOnWorkerThread(new Runnable() {
            @Override
            public void run() {
                callAnswerOnTask(answer, callback);
            }
        });
    }

    private void callAnswerOnTask(final QuizAnswer answer, final WeakReference<AnswerQuestionTaskCallback> callback) {
        task.answer(answer, new AnswerQuestionTaskCallback() {
            @Override
            public void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered) {
                callbackRightAnswerGiven(correctAnswers, questionsAnswered, callback.get());
            }

            @Override
            public void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered) {
                callbackWrongAnswerGiven(correctAnswers, questionsAnswered, callback.get());
            }
        });
    }

    private void callbackRightAnswerGiven(final int correctAnswers, final int questionsAnswered, final AnswerQuestionTaskCallback callback) {
        runOnCallbackThread(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onRightAnswerGiven(correctAnswers, questionsAnswered);
                }
            }
        });
    }

    private void callbackWrongAnswerGiven(final int correctAnswers, final int questionsAnswered, final AnswerQuestionTaskCallback callback) {
        runOnCallbackThread(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onWrongAnswerGiven(correctAnswers, questionsAnswered);
                }
            }
        });
    }
}
