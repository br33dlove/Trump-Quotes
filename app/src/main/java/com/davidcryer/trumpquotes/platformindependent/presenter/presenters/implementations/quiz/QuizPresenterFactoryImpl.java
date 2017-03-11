package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.quiz;

import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuizPresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuizView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

public class QuizPresenterFactoryImpl<ViewQuoteType extends ViewQuestion> implements QuizPresenterFactory<ViewQuoteType> {
    private final ViewQuestionFactory<ViewQuoteType> viewQuestionFactory;
    private final InteractorFactory interactorFactory;

    public QuizPresenterFactoryImpl(
            final ViewQuestionFactory<ViewQuoteType> viewQuestionFactory,
            final InteractorFactory interactorFactory
    ) {
        this.viewQuestionFactory = viewQuestionFactory;
        this.interactorFactory = interactorFactory;
    }

    @Override
    public Presenter<QuizView.EventsListener> create(final QuizView<ViewQuoteType> viewWrapper) {
        return new QuizPresenter<>(viewWrapper, viewQuestionFactory, interactorFactory.createLoadGameInteractor(), interactorFactory.createInitialiseGameInteractor());
    }
}
