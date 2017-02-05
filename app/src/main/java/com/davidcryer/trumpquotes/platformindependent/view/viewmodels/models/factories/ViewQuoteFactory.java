package com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface ViewQuoteFactory<ViewQuoteType extends ViewQuote> {
    ViewQuoteType create(final Quote quote);
    ViewQuoteType[] createArray(final Quote[] quotes);
}
