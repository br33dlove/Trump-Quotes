package com.davidcryer.trumpquotes.android.view.ui.helpers;

import android.view.View;

public class ViewHelper {

    public static boolean coordinatesAreInView(final float x, final float y, final View view) {
        return x >= view.getX() && x <= view.getX() + view.getWidth() && y >= view.getY() && y <= view.getY() + view.getHeight();
    }
}
