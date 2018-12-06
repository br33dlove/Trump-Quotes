package com.davidcryer.trumpquotes.android.model.tasks;

public interface AnswerQuestionTaskCallback {
    void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered);
    void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered);
}
