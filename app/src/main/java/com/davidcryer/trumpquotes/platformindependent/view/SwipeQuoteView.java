package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface SwipeQuoteView<ViewQuoteType extends ViewQuote> extends MvpView<SwipeQuoteMvpViewModel<ViewQuoteType>> {
    void showQuoteState(final ViewQuoteType quotes);
    void showLoadingQuotesState();
    void showFailureToGetQuotesState();
    void showScore(final int correctAnswerCount, final int questionCount);

    interface EventsListener extends MvpView.EventsListener {
        void onStartGame();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
