package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuotesMvpViewModel;

import java.util.List;

public interface QuotesAndroidViewModel extends QuotesMvpViewModel<AndroidViewQuote>, AndroidViewModel<QuotesAndroidView> {
    void showFailureToGetNewQuote(final QuotesAndroidView view);
    void showLoadingNewQuote(final QuotesAndroidView view);
    void hideLoadingNewQuote(final QuotesAndroidView view);
    void showNewQuote(final QuotesAndroidView view, final AndroidViewQuote quote);
    void addNewQuoteToHistory(final QuotesAndroidView view, final AndroidViewQuote quote);
    void showQuoteHistory(final QuotesAndroidView view, final List<AndroidViewQuote> quotes);
    void removeQuoteInHistory(final QuotesAndroidView view, final AndroidViewQuote quote);
    void removeAllQuotesInHistory(final QuotesAndroidView view);
    int indexOfQuoteInHistory(final AndroidViewQuote viewQuote);
}
