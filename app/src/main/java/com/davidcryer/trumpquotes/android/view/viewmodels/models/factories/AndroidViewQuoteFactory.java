package com.davidcryer.trumpquotes.android.view.viewmodels.models.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuoteImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class AndroidViewQuoteFactory implements ViewQuoteFactory<AndroidViewQuote> {

    @Override
    public AndroidViewQuote create(Quote quote) {
        return AndroidViewQuoteImpl.newInstance(quote.id(), quote.text());
    }

    @Override
    public AndroidViewQuote[] createArray(Quote[] quotes) {
        final AndroidViewQuote[] viewQuotes = new AndroidViewQuote[quotes.length];
        for (int i = 0; i < quotes.length; i++) {
            viewQuotes[i] = create(quotes[i]);
        }
        return viewQuotes;
    }
}
