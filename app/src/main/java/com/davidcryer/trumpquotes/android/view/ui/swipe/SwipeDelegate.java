package com.davidcryer.trumpquotes.android.view.ui.swipe;

import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

public class SwipeDelegate implements GestureDetector.OnGestureListener {
    private final static float RETURN_TO_ORIGIN_PIXEL_PER_MS = 0.5f;
    private final View view;
    private final View parent;
    private final Listener listener;
    private final GestureDetector gestureDetector;
    private final Scroller scroller;
    @Nullable private ValueAnimator flingAnimator;
    @Nullable private ViewPropertyAnimator toOriginAnimator;
    private boolean listenForGestures;

    public SwipeDelegate(View view, View parent, Listener listener) {
        this.view = view;
        this.parent = parent;
        this.listener = listener;
        gestureDetector = new GestureDetector(view.getContext(), this);
        scroller = new Scroller(view.getContext());
        listenForGestures = true;
    }

    public void listenForGestures(final boolean listenForGestures) {
        this.listenForGestures = listenForGestures;
    }

    public boolean onTouch(final MotionEvent event) {
        return listenForGestures && (gestureDetector.onTouchEvent(event) || onMotionUp(event));
    }

    private boolean onMotionUp(final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            stopCardMovement();
            animateViewBackToOrigin();
            return true;
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        stopCardMovement();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    private void animateViewBackToOrigin() {
        stopCardMovement();
        final ViewGroup.MarginLayoutParams viewLp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        final int xOrigin = viewLp.leftMargin;
        final int yOrigin = viewLp.topMargin;
        toOriginAnimator = view.animate()
                .x(xOrigin)
                .y(yOrigin)
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        sendOnCardMovedCallback(view.getX());
                    }
                })
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(durationBackToOrigin());
        toOriginAnimator.start();
    }

    private long durationBackToOrigin() {
        return (long) (Math.pow(Math.pow(view.getX(), 2) + Math.pow(view.getY(), 2), 0.5) * RETURN_TO_ORIGIN_PIXEL_PER_MS);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        stopCardMovement();
        final float newX = view.getX() - distanceX;
        view.setX(newX);
        view.setY(view.getY() - distanceY);
        sendOnCardMovedCallback(newX);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        fling((int) velocityX, (int) velocityY);
        return true;
    }

    private void fling(final int velocityX, final int velocityY) {
        stopCardMovement();
        final int minX = -view.getWidth() - 100;
        final int maxX = parent.getWidth() + 100;
        scroller.fling((int) view.getX(), (int) view.getY(), velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        final int finalX = scroller.getFinalX();
        if (finalX <= minX || finalX >= maxX) {
            animateFling(minX, maxX);
        } else {
            animateViewBackToOrigin();
        }
    }

    private void animateFling(final int minX, final int maxX) {
        flingAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        assert flingAnimator != null;
        flingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!scroller.isFinished()) {
                    scroller.computeScrollOffset();
                    final int currentX = scroller.getCurrX();
                    final int currentY = scroller.getCurrY();
                    view.setX(currentX);
                    view.setY(currentY);
                    sendOnCardMovedCallback(currentX);
                    final boolean viewEscapedLeft = currentX <= minX;
                    final boolean viewEscapedRight = currentX >= maxX;
                    if (viewEscapedLeft || viewEscapedRight) {
                        viewEscapedBounds(viewEscapedLeft);
                    }
                } else {
                    stopCardMovement();
                    final boolean viewNotEscapedLeft = view.getX() > minX;
                    final boolean viewNotEscapedRight = view.getX() < maxX;
                    if (viewNotEscapedLeft && viewNotEscapedRight) {
                        animateViewBackToOrigin();
                    }
                }
            }
        });
        flingAnimator.setDuration(scroller.getDuration());
        flingAnimator.setInterpolator(new LinearInterpolator());
        flingAnimator.start();
    }

    private void viewEscapedBounds(final boolean escapedLeft) {
        stopCardMovement();
        view.clearAnimation();
        if (escapedLeft) {
            listener.onViewEscapedLeft(view);
        } else {
            listener.onViewEscapedRight(view);
        }
    }

    private void stopCardMovement() {
        scroller.forceFinished(true);
        if (toOriginAnimator != null) {
            toOriginAnimator.cancel();
            toOriginAnimator = null;
        }
        if (flingAnimator != null) {
            flingAnimator.cancel();
            flingAnimator = null;
        }
    }

    private void sendOnCardMovedCallback(final float currentX) {
        final float parentWidth = parent.getWidth();
        final float viewCentreX = currentX + view.getWidth() / 2.0f;
        final float parentCentreX = parentWidth / 2.0f;
        final float percentageOffsetFromCentreX = (viewCentreX - parentCentreX) / parentWidth;
        listener.onCardMoved(percentageOffsetFromCentreX);
    }

    public interface Listener {
        void onViewEscapedLeft(final View child);
        void onViewEscapedRight(final View child);
        void onCardMoved(final float percentageOffsetFromCentreX);
    }
}
