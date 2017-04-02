package com.davidcryer.trumpquotes.android.view.ui.helpers.nongeneric;

import android.content.res.Resources;
import android.view.View;

import com.davidcryer.trumpquotes.R;
import com.davidcryer.trumpquotes.android.view.ui.helpers.OnGlobalLayoutHelper;
import com.davidcryer.trumpquotes.android.view.ui.helpers.SlideInAndOffScreenHelper;

public class ScoreViewAnimationHelper {
    private final static int ANIMATION_DURATION_MAX = 500;

    public static void slideIn(final View view, final View root) {
        if (view.getVisibility() == View.GONE) {
            OnGlobalLayoutHelper.listen(view, new OnGlobalLayoutHelper.PreLayoutCallback() {
                @Override
                public void onPreLayout() {
                    view.setVisibility(View.VISIBLE);
                }
            }, new OnGlobalLayoutHelper.PostLayoutCallback() {
                @Override
                public void onPostLayout() {
                    final int rootWidth = root.getWidth();
                    slideIn(view, rootWidth, animateInFinalX(view.getWidth(), rootWidth, view.getResources()), 0);
                }
            });
        } else {
            final float currentX = view.getX();
            final int viewWidth = view.getWidth();
            final int rootWidth = root.getWidth();
            final float finalX = animateInFinalX(viewWidth, rootWidth, view.getResources());
            slideIn(view, currentX, finalX, currentX - rootWidth);
        }
    }

    private static float animateInFinalX(final int viewWidth, final int rootWidth, final Resources resources) {
        return rootWidth - (viewWidth + resources.getDimensionPixelOffset(R.dimen.score_margin_end));
    }

    private static void slideIn(final View view, final float startX, final float finalX, final float offsetStartX) {
        if (startX != finalX) {
            SlideInAndOffScreenHelper.slideInX(view, startX, finalX, duration(startX, finalX, offsetStartX));
        }
    }

    private static int duration(final float startX, final float finalX, final float offsetStartX) {
        return (int) ((startX - finalX) / (startX - offsetStartX - finalX) * ANIMATION_DURATION_MAX);
    }

    public static void slideOut(final View view, final View root) {
        view.setEnabled(false);
        if (view.getVisibility() == View.VISIBLE) {
            final float currentX = view.getX();
            final int rootWidth = root.getWidth();
            slideOut(view, currentX, rootWidth, currentX - animateInFinalX(view.getWidth(), rootWidth, view.getResources()));
        }
    }

    private static void slideOut(final View view, final float startX, final float finalX, final float offsetStartX) {
        if (startX != finalX) {
            SlideInAndOffScreenHelper.slideOutX(view, startX, finalX, duration(startX, finalX, offsetStartX));
        }
    }
}
