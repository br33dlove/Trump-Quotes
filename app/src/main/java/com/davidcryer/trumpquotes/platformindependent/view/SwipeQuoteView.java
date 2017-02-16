package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

import java.util.List;

public interface SwipeQuoteView<ViewQuoteType extends ViewQuote> extends MvpView<SwipeQuoteMvpViewModel<ViewQuoteType>> {
    void showLoadingQuote();
    void hideLoadingQuote();
    void showQuote(final ViewQuoteType quote);
    void hideFailureToGetQuote();
    void showFailureToGetQuote();

    interface EventsListener extends MvpView.EventsListener {
        void onRequestFirstQuote();
        void onRetryQuoteRequest();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
