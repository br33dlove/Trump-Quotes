package com.davidcryer.trumpquotes.platformindependent.javahelpers;

public class StringHelper {

    public static boolean nullOrEmpty(final String string) {
        return string == null || string.isEmpty();
    }

    public static int[] splitDelimitedInts(final String string, final String delimiterRegex) {
        if (string == null) {
            return null;
        }
        final String[] parts = string.split(delimiterRegex);
        final int[] ints = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ints[i] = Integer.parseInt(parts[i]);
        }
        return ints;
    }
}
