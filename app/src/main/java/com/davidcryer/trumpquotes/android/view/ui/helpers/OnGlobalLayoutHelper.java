package com.davidcryer.trumpquotes.android.view.ui.helpers;

import android.view.View;
import android.view.ViewTreeObserver;

public class OnGlobalLayoutHelper {

    public static void listen(final View view, final PostLayoutCallback callback) {
        listen(view, null, callback);
    }

    public static void listen(final View view, final PreLayoutCallback preLayoutCallback, final PostLayoutCallback postLayoutCallback) {
        if (preLayoutCallback != null) {
            preLayoutCallback.onPreLayout();
        }
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (postLayoutCallback != null) {
                    postLayoutCallback.onPostLayout();
                }
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public interface PostLayoutCallback {
        void onPostLayout();
    }

    public interface PreLayoutCallback {
        void onPreLayout();
    }
}
