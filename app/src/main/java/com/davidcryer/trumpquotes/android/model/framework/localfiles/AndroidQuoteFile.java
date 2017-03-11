package com.davidcryer.trumpquotes.android.model.framework.localfiles;

import android.content.res.AssetManager;

import com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.QuoteFile;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;
import com.google.gson.Gson;

import java.io.IOException;

public class AndroidQuoteFile<QuoteType extends Quote> implements QuoteFile {
    private final String filePath;
    private final AssetManager assetManager;
    private final Class<QuoteType[]> quoteArrayClass;
    private final Gson gson;

    public AndroidQuoteFile(String filePath, AssetManager assetManager, Class<QuoteType[]> quoteArrayClass, Gson gson) {
        this.filePath = filePath;
        this.assetManager = assetManager;
        this.quoteArrayClass = quoteArrayClass;
        this.gson = gson;
    }

    @Override
    public Quote[] quotes() throws IOException {
        return gson.fromJson(TextFileReader.reader(filePath, assetManager), quoteArrayClass);
    }
}
