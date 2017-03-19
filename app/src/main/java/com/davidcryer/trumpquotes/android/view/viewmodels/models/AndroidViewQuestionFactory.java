package com.davidcryer.trumpquotes.android.view.viewmodels.models;

import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

public class AndroidViewQuestionFactory implements ViewQuestionFactory<AndroidViewQuestion> {

    @Override
    public AndroidViewQuestion create(QuizQuestion quizQuestion) {
        return new AndroidViewQuestionImpl(quizQuestion.quote(), quizQuestion.optionA(), quizQuestion.optionB());
    }
}
