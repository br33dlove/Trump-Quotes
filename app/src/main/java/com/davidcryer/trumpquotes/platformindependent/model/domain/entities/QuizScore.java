package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public interface QuizScore {
    int questionsAnswered();
    int correctAnswers();
    void onRightAnswerGiven();
    void onWrongAnswerGiven();
}
