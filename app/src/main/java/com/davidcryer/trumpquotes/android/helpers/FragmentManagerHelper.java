package com.davidcryer.trumpquotes.android.helpers;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentManagerHelper {

    public static boolean noFragmentBoundToView(
            final FragmentManager fragmentManager,
            @IdRes final int view
    ) {
        return fragmentManager.findFragmentById(view) == null;
    }

    public static void addFragment(
            final FragmentManager fragmentManager,
            final Fragment fragment,
            final String fragmentTag
    ) {
       fragmentManager
               .beginTransaction()
               .add(fragment, fragmentTag)
               .commit();
    }

    public static void addFragment(
            final FragmentManager fragmentManager,
            final Fragment fragment,
            @IdRes final int view
    ) {
        fragmentManager
                .beginTransaction()
                .add(view, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static boolean hasMoreThanOneNonRetainedFragment(final FragmentManager fragmentManager) {
        return nonRetainedFragmentCount(fragmentManager) > 1;
    }

    public static int nonRetainedFragmentCount(final FragmentManager fragmentManager) {
        int nonRetainedFragmentCount = 0;
        for (final Fragment fragment : fragmentManager.getFragments()) {
            if (!fragment.getRetainInstance()) {
                nonRetainedFragmentCount++;
            }
        }
        return nonRetainedFragmentCount;
    }

    public static boolean hasFragmentForTag(final FragmentManager fragmentManager, final String fragmentTag) {
        return fragmentManager.findFragmentByTag(fragmentTag) != null;
    }
}
