package com.davidcryer.trumpquotes.platformindependent.view.viewmodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface QuoteHistoryMvpViewModel<ViewQuoteType extends ViewQuote> extends MvpViewModel {
    ViewQuoteType[] quoteHistory();
}
