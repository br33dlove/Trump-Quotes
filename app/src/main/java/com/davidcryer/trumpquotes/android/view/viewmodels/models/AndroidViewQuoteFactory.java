package com.davidcryer.trumpquotes.android.view.viewmodels.models;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

import java.util.LinkedList;
import java.util.List;

public class AndroidViewQuoteFactory implements ViewQuoteFactory<AndroidViewQuestion> {

    @Override
    public AndroidViewQuestion create(Quote quote) {
        return new AndroidViewQuestionImpl(quote.id(), quote.text());
    }

    @Override
    public List<AndroidViewQuestion> create(List<Quote> quotes) {
        final List<AndroidViewQuestion> viewQuotes = new LinkedList<>();
        for (final Quote quote : quotes) {
            viewQuotes.add(create(quote));
        }
        return viewQuotes;
    }
}
