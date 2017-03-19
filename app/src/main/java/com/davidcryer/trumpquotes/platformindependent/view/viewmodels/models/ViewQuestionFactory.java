package com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models;

import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizQuestion;

public interface ViewQuestionFactory<ViewQuestionType extends ViewQuestion> {
    ViewQuestionType create(final QuizQuestion quizQuestion);
}
