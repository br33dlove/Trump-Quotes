package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

public class SwipeQuotePresenterFactoryImpl<ViewQuoteType extends ViewQuestion> implements SwipeQuotePresenterFactory<ViewQuoteType> {
    private final ViewQuestionFactory<ViewQuoteType> viewQuestionFactory;
    private final InteractorFactory interactorFactory;

    public SwipeQuotePresenterFactoryImpl(
            final ViewQuestionFactory<ViewQuoteType> viewQuestionFactory,
            final InteractorFactory interactorFactory
    ) {
        this.viewQuestionFactory = viewQuestionFactory;
        this.interactorFactory = interactorFactory;
    }

    @Override
    public Presenter<SwipeQuestionView.EventsListener> create(final SwipeQuestionView<ViewQuoteType> viewWrapper) {
        return new SwipeQuotePresenter<>(viewWrapper, viewQuestionFactory, interactorFactory.createLoadGameInteractor(), interactorFactory.createInitialiseGameInteractor());
    }
}
