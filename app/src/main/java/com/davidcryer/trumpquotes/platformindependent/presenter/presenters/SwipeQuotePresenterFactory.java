package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;

public interface SwipeQuotePresenterFactory<ViewQuoteType extends ViewQuestion> {
    Presenter<SwipeQuestionView.EventsListener> create(final SwipeQuestionView<ViewQuoteType> viewWrapper);
}
