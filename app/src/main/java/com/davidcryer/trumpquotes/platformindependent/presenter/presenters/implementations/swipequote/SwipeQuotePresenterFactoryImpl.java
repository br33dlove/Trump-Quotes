package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

public class SwipeQuotePresenterFactoryImpl<ViewQuoteType extends ViewQuestion> implements SwipeQuotePresenterFactory<ViewQuoteType> {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public SwipeQuotePresenterFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public Presenter<SwipeQuestionView.EventsListener> create(final SwipeQuestionView<ViewQuoteType> viewWrapper) {
        return new SwipeQuotePresenter<>(viewWrapper, quoteRequesterFactory.createRandomQuoteRequester(), viewQuoteFactory);
    }
}
