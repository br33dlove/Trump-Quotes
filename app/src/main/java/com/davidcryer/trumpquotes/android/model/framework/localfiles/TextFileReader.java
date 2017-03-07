package com.davidcryer.trumpquotes.android.model.framework.localfiles;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class TextFileReader {

    public static String text(final String filepath, final AssetManager assetManager) {
        try {
            final StringBuilder stringBuilder = new StringBuilder();
            final InputStream json = assetManager.open(filepath);
            final BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                stringBuilder.append(str);
            }
            in.close();
            return stringBuilder.toString();
        } catch (IOException ioe) {
            return "";
        }
    }

    static Reader reader(final String filepath, final AssetManager assetManager) throws IOException {
        return new InputStreamReader(assetManager.open(filepath));
    }
}
