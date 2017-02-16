package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.QuoteHistoryAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuoteHistoryMvpViewModel;

import java.util.List;

public interface QuoteHistoryAndroidViewModel extends QuoteHistoryMvpViewModel<AndroidViewQuote>, AndroidViewModel<QuoteHistoryAndroidView> {
    void showQuoteHistory(final QuoteHistoryAndroidView view, final List<AndroidViewQuote> quotes);
    void removeQuoteInHistory(final QuoteHistoryAndroidView view, final AndroidViewQuote quote);
    void removeAllQuotesInHistory(final QuoteHistoryAndroidView view);
    int indexOfQuoteInHistory(final AndroidViewQuote viewQuote);
}
