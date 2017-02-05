package com.davidcryer.mvpandroid.platformindependent.javahelpers;

public class StringHelper {

    public static boolean nullOrEmpty(final String string) {
        return string == null || string.isEmpty();
    }
}
