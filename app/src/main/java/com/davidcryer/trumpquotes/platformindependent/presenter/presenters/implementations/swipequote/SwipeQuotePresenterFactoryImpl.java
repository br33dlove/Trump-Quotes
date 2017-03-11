package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

public class SwipeQuotePresenterFactoryImpl<ViewQuoteType extends ViewQuestion> implements SwipeQuotePresenterFactory<ViewQuoteType> {
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;
    private final InteractorFactory interactorFactory;

    public SwipeQuotePresenterFactoryImpl(
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory,
            final InteractorFactory interactorFactory
    ) {
        this.viewQuoteFactory = viewQuoteFactory;
        this.interactorFactory = interactorFactory;
    }

    @Override
    public Presenter<SwipeQuestionView.EventsListener> create(final SwipeQuestionView<ViewQuoteType> viewWrapper) {
        return new SwipeQuotePresenter<>(viewWrapper, viewQuoteFactory, interactorFactory.createLoadGameInteractor(), interactorFactory.createInitialiseGameInteractor());
    }
}
