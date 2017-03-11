package com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestion;

public interface ViewQuoteFactory<ViewQuestionType extends ViewQuestion> {
    ViewQuestionType create(final QuizQuestion quizQuestion);
}
