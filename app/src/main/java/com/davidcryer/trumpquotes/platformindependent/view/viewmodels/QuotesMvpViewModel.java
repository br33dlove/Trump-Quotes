package com.davidcryer.trumpquotes.platformindependent.view.viewmodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface QuotesMvpViewModel<ViewQuoteType extends ViewQuote> extends MvpViewModel {
    ViewQuoteType newQuote();
    ViewQuoteType[] quoteHistory();
}
