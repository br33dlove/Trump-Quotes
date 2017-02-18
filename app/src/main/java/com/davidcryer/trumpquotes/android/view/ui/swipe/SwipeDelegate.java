package com.davidcryer.trumpquotes.android.view.ui.swipe;

import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class SwipeDelegate implements GestureDetector.OnGestureListener {
    private final static float TAP_UP_ESCAPE_VELOCITY = 20f;//TODO find appropriate value
    private final static float RETURN_TO_ORIGIN_PIXEL_PER_MS = 0.5f;
    private final View view;
    private final View parent;
    private final Listener listener;
    private final GestureDetector gestureDetector;
    private final Scroller scroller;
    @Nullable private ValueAnimator flingAnimator;

    public SwipeDelegate(View view, View parent, Listener listener) {
        this.view = view;
        this.parent = parent;
        this.listener = listener;
        gestureDetector = new GestureDetector(view.getContext(), this);
        scroller = new Scroller(view.getContext());
        init();
    }

    private void init() {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        stopScrollAndFling();
        view.clearAnimation();
        ViewCompat.postInvalidateOnAnimation(view);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        scroller.computeScrollOffset();
        if (scroller.isFinished() || scroller.getCurrVelocity() < TAP_UP_ESCAPE_VELOCITY) {
            animateViewBackToOrigin();
        }
        return true;
    }

    private void animateViewBackToOrigin() {
        stopScrollAndFling();
        view.animate().x(0).y(0).setDuration(durationBackToOrigin()).start();
    }

    private long durationBackToOrigin() {
        return (long) (Math.pow(Math.pow(view.getX(), 2) + Math.pow(view.getY(), 2), 0.5) * RETURN_TO_ORIGIN_PIXEL_PER_MS);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        stopScrollAndFling();
        view.animate().xBy(distanceX).yBy(distanceY).start();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        fling((int) -velocityX, (int) -velocityY);
        return true;
    }

    private void fling(final int velocityX, final int velocityY) {
        stopScrollAndFling();
        view.clearAnimation();
        final int minX = -view.getWidth();
        final int maxX = parent.getWidth();
        final int minY = -view.getHeight();
        final int maxY = parent.getHeight();
        scroller.fling((int) view.getX(), (int) view.getY(), velocityX, velocityY, minX, maxX, minY, maxY);
        final int finalX = scroller.getFinalX();
        if (finalX <= minX || finalX >= maxX) {
            animateFling(minX, maxX);
        } else {
            animateViewBackToOrigin();
        }
    }

    private void animateFling(final int minX, final int maxX) {
        flingAnimator = ValueAnimator.ofFloat(0, 1);
        assert flingAnimator != null;
        flingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!scroller.isFinished()) {
                    scroller.computeScrollOffset();
                    final int currentX = scroller.getCurrX();
                    final int currentY = scroller.getCurrY();
                    final boolean viewEscapedLeft = currentX <= minX;
                    final boolean viewEscapedRight = currentX >= maxX;
                    if (viewEscapedLeft || viewEscapedRight) {
                        viewEscapedBounds(viewEscapedLeft);
                    } else {
                        view.animate().x(currentX).y(currentY).start();
                    }
                } else {
                    stopScrollAndFling();
                }
            }
        });
        flingAnimator.setDuration(scroller.getDuration());
        flingAnimator.start();
        ViewCompat.postInvalidateOnAnimation(view);
    }

    private void viewEscapedBounds(final boolean escapedLeft) {
        stopScrollAndFling();
        view.clearAnimation();
        if (escapedLeft) {
            listener.onViewEscapedLeft(view);
        } else {
            listener.onViewEscapedRight(view);
        }
    }

    private void stopScrollAndFling() {
        scroller.forceFinished(true);
        if (flingAnimator != null) {
            flingAnimator.cancel();
            flingAnimator = null;
        }
    }

    public interface Listener {
        void onViewEscapedLeft(final View child);
        void onViewEscapedRight(final View child);
    }
}
