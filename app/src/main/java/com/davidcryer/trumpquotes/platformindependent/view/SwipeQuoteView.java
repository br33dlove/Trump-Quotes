package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

import java.util.List;

public interface SwipeQuoteView<ViewQuoteType extends ViewQuote> extends MvpView<SwipeQuoteMvpViewModel<ViewQuoteType>> {
    void showQuoteState(final ViewQuoteType quote);
    void showLoadingQuoteState();
    void showFailureToGetQuoteState();

    interface EventsListener extends MvpView.EventsListener {
        void onRequestFirstQuote();
        void onRetryQuoteRequest();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
