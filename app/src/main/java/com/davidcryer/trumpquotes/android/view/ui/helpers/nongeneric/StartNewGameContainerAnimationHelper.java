package com.davidcryer.trumpquotes.android.view.ui.helpers.nongeneric;

import android.view.View;

import com.davidcryer.trumpquotes.android.view.ui.helpers.OnLayoutHelper;
import com.davidcryer.trumpquotes.android.view.ui.helpers.SlideInAndOffScreenHelper;

public class StartNewGameContainerAnimationHelper {
    private final static int ANIMATION_DURATION_MAX = 500;

    public static void slideIn(final View view, final View root) {
        if (view.getVisibility() == View.GONE) {
            OnLayoutHelper.layout(view, new OnLayoutHelper.Callback() {
                @Override
                public void preLayout() {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void postLayout() {
                    final int rootHeight = root.getHeight();
                    slideIn(view, rootHeight, animateInFinalY(view.getHeight(), rootHeight), 0);
                }
            });
        } else {
            final float currentY = view.getY();
            final int viewHeight = view.getHeight();
            final int rootHeight = root.getHeight();
            final float finalY = animateInFinalY(viewHeight, rootHeight);
            slideIn(view, currentY, finalY, currentY - rootHeight);
        }
    }

    private static float animateInFinalY(final int viewHeight, final int rootHeight) {
        return (rootHeight - viewHeight) / 2.0f;
    }

    private static void slideIn(final View view, final float startY, final float finalY, final float offsetStartY) {
        if (startY != finalY) {
            SlideInAndOffScreenHelper.slideInY(view, startY, finalY, duration(startY, finalY, offsetStartY));
        }
    }

    private static int duration(final float startY, final float finalY, final float offsetStartY) {
        return (int) ((startY - finalY) / (startY - offsetStartY - finalY) * ANIMATION_DURATION_MAX);
    }

    public static void slideOut(final View view, final View root) {
        view.setEnabled(false);
        if (view.getVisibility() == View.VISIBLE) {
            final float currentY = view.getY();
            final int rootHeight = root.getHeight();
            slideOut(view, currentY, rootHeight, currentY - animateInFinalY(view.getHeight(), rootHeight));
        }
    }

    private static void slideOut(final View view, final float startY, final float finalY, final float offsetStartY) {
        if (startY != finalY) {
            SlideInAndOffScreenHelper.slideOutY(view, startY, finalY, duration(startY, finalY, offsetStartY));
        }
    }
}
