package com.davidcryer.trumpquotes.android.view.ui.helpers;

import android.view.View;
import android.view.ViewTreeObserver;

public class OnLayoutHelper {

    public static void layout(final View view, final Callback callback) {
        callback.preLayout();
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                callback.postLayout();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public interface Callback {
        void preLayout();
        void postLayout();
    }
}
