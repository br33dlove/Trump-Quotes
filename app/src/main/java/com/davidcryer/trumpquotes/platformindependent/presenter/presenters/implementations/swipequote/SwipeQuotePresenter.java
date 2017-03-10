package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.ActiveGameInteractors;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

class SwipeQuotePresenter<ViewQuoteType extends ViewQuote> extends Presenter<SwipeQuoteView.EventsListener> {
    private final SwipeQuoteView<ViewQuoteType> viewWrapper;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;
    private final InitialiseGameInteractor initialiseGameInteractor;
    private ActiveGameInteractors activeGameInteractors;

    SwipeQuotePresenter(
            final SwipeQuoteView<ViewQuoteType> viewWrapper,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        this.viewWrapper = viewWrapper;
        this.viewQuoteFactory = viewQuoteFactory;
        this.initialiseGameInteractor = initialiseGameInteractor;
    }

    @Override
    public SwipeQuoteView.EventsListener eventsListener() {
        return new SwipeQuoteView.EventsListener() {

            @Override
            public void onStartGame() {
                showLoadingQuote();
                initialiseGameAndDisplay();
            }

            @Override
            public void onQuoteSwipedLeft() {
                showLoadingQuote();
                updateQuoteAsJudgedInStore();
                requestQuoteAndDisplay(false);
            }

            @Override
            public void onQuoteSwipedRight() {
                showLoadingQuote();
                updateQuoteAsJudgedInStore();
                requestQuoteAndDisplay(false);
            }

            @Override
            public void onReleaseResources(final boolean isFinishing) {
                deregisterFromOngoingQuoteRequests(isFinishing);
            }
        };
    }

    private void initialiseGameAndDisplay() {
        initialiseGameInteractor.
    }

    private void showLoadingQuote() {
        viewWrapper.showLoadingQuotesState();
    }

    private void requestQuoteAndDisplay(final boolean preferLastReceivedQuote) {
        randomQuoteRequester.requestRandomQuote(quoteCallback, preferLastReceivedQuote);
    }

    private void updateQuoteAsJudgedInStore() {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().quote();
        quoteRepositoryHandler.updateQuoteAsJudged(viewQuote.id());
    }

    private void deregisterFromOngoingQuoteRequests(final boolean shouldCancelRequests) {
        randomQuoteRequester.remove(quoteCallback, shouldCancelRequests);
    }

    private final QuoteRequestCallback quoteCallback = new QuoteRequestCallback() {

        @Override
        public void success(Quote quote) {
            viewWrapper.showQuoteState(viewQuoteFactory.create(quote));
            quoteRepositoryHandler.store(quote);
        }

        @Override
        public void failure() {
            viewWrapper.showFailureToGetQuotesState();
        }
    };
}
