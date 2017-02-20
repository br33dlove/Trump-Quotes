package com.davidcryer.trumpquotes.android.view.ui.helpers;

import android.view.View;

public class AlphaAnimationHelper {

    public static void fadeOut(final View view, final long maxDuration) {
        if (view.getVisibility() != View.GONE) {
            view.animate()
                    .alpha(0)
                    .setDuration(alphaAnimationDuration(view.getAlpha(), 0, maxDuration))
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.GONE);
                        }
                    })
                    .start();
        }
    }

    public static void fadeIn(final View view, final long maxDuration) {
        if (view.getVisibility() != View.VISIBLE) {
            view.animate()
                    .alpha(1)
                    .setDuration(alphaAnimationDuration(view.getAlpha(), 1, maxDuration))
                    .withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();
        }
    }

    private static long alphaAnimationDuration(final float start, final float end, final long maxDuration) {
        return (long) Math.abs(start - end) * maxDuration;
    }
}
