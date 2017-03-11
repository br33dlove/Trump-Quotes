package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.view.QuizView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;

public interface QuizPresenterFactory<ViewQuoteType extends ViewQuestion> {
    Presenter<QuizView.EventsListener> create(final QuizView<ViewQuoteType> viewWrapper);
}
