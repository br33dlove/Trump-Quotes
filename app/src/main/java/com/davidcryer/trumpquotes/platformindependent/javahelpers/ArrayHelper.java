package com.davidcryer.trumpquotes.platformindependent.javahelpers;

public class ArrayHelper {

    public static String[] toStringArray(final int[] ints) {
        final String[] array = new String[ints.length];
        for (int i = 0; i < ints.length; i++) {
            array[i] = String.valueOf(ints[i]);
        }
        return array;
    }

    public static String toString(final int[] ints, final String delimiter) {
        String string = "";
        for (int i = 0; i < ints.length; i++) {
            string += String.valueOf(ints[i]);
            if (i < ints.length - 1) {
                string += delimiter;
            }
        }
        return string;
    }
}
