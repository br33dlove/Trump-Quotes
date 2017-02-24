package com.davidcryer.trumpquotes.platformindependent.model.quizgame;

public interface QuizScore {
    int questionsAnswered();
    int correctAnswers();
    void onCorrectAnswerGiven();
    void onIncorrectAnswerGiven();
}
