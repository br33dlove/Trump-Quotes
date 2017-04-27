package com.davidcryer.trumpquotes.android.model.framework.localfiles;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class TextFileReader {

    static Reader reader(final String filepath, final AssetManager assetManager) throws IOException {
        return new InputStreamReader(assetManager.open(filepath));
    }
}
