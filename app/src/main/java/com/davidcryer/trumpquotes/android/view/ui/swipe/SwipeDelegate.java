package com.davidcryer.trumpquotes.android.view.ui.swipe;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDelegate implements GestureDetector.OnGestureListener {
    private final View view;
    private final GestureDetector gestureDetector;
    private final Listener listener;

    public SwipeDelegate(View view, Listener listener) {
        this.view = view;
        this.listener = listener;
        gestureDetector = new GestureDetector(view.getContext(), this);
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
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public interface Listener {

    }
}
