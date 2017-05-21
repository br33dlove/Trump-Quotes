package com.davidcryer.trumpquotes.android.model.domainentities;

interface QuizScore {
    int questionsAnswered();
    int correctAnswers();
    void onRightAnswerGiven();
    void onWrongAnswerGiven();
}
