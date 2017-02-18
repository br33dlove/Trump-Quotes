package com.davidcryer.trumpquotes.android.view.viewmodels.models.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuoteImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AndroidViewQuoteFactory implements ViewQuoteFactory<AndroidViewQuote> {

    @Override
    public AndroidViewQuote create(Quote quote) {
        return AndroidViewQuoteImpl.newInstance(quote.id(), quote.text());
    }

    @Override
    public List<AndroidViewQuote> create(List<Quote> quotes) {
        final List<AndroidViewQuote> viewQuotes = new LinkedList<>();
        for (final Quote quote : quotes) {
            viewQuotes.add(create(quote));
        }
        return viewQuotes;
    }
}
