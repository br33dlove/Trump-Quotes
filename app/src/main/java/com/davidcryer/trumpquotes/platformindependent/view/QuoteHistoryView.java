package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuoteHistoryMvpViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

import java.util.List;

public interface QuoteHistoryView<ViewQuoteType extends ViewQuote> extends MvpView<QuoteHistoryMvpViewModel<ViewQuoteType>> {
    void showQuoteHistory(final List<ViewQuoteType> quotes);
    void removeQuoteInHistory(final ViewQuoteType quote);
    void removeAllQuotesInHistory();

    interface EventsListener extends MvpView.EventsListener {
        void onRequestQuoteHistory();
        void onDeleteQuoteInHistoryClicked(final int index);
        void onDeleteAllQuotesClicked();
    }
}
