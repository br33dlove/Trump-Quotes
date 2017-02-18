package com.davidcryer.trumpquotes.android.view.ui.helpers;

import java.util.ArrayList;
import java.util.Collections;

public class ListHelper {

    public static <Type> ArrayList<Type> asArrayList(final Type[] typeArray) {
        if (typeArray == null) {
            return new ArrayList<>();
        }
        final ArrayList<Type> list = new ArrayList<>(typeArray.length);
        Collections.addAll(list, typeArray);
        return list;
    }
}
