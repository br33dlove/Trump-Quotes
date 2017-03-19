package com.davidcryer.trumpquotes.platformindependent.model.domainentities;

interface QuizScore {
    int questionsAnswered();
    int correctAnswers();
    void onRightAnswerGiven();
    void onWrongAnswerGiven();
}
