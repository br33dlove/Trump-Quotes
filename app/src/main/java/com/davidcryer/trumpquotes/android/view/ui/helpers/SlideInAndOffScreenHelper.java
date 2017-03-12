package com.davidcryer.trumpquotes.android.view.ui.helpers;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class SlideInAndOffScreenHelper {

    public static void slideInY(final View view, final float startY, final float finalY, final int duration) {
        slideY(view, startY, finalY, duration, new DecelerateInterpolator(), null);
    }

    public static void slideOutY(final View view, final float startY, final float finalY, final int duration) {
        slideY(view, startY, finalY, duration, new AccelerateInterpolator(), new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        });
    }

    private static void slideY(
            final View view,
            final float startY,
            final float finalY,
            final int duration,
            final Interpolator interpolator,
            final Runnable endAction
    ) {
        view.setY(startY);
        view.animate()
                .y(finalY)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .withEndAction(endAction)
                .start();
    }
}
