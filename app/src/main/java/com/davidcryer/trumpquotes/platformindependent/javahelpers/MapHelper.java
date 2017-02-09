package com.davidcryer.trumpquotes.platformindependent.javahelpers;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.Map;

public class MapHelper {

    public static <K, V, M extends Map<K, LinkedList<V>>> void forKeyAddValueToLinkedListInMap(final K key, final V value, @NonNull final M map) {
        LinkedList<V> list = map.get(key);
        if (list == null) {
            list = new LinkedList<>();
            list.add(value);
            map.put(key, list);
        } else {
            list.add(value);
        }
    }
}
