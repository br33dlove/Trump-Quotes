package com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

import java.util.List;

public interface ViewQuoteFactory<ViewQuoteType extends ViewQuote> {
    ViewQuoteType create(final Quote quote);
    List<ViewQuoteType> create(final List<Quote> quotes);
}
