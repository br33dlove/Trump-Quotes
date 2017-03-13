package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;

public class SingleChildSwipeLayout extends SwipeLayout {
    private SwipeDelegate swipeDelegate;
    private SwipeDelegate.Listener swipeListener;

    public SingleChildSwipeLayout(Context context) {
        super(context);
        init();
    }

    public SingleChildSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return swipeDelegate != null && swipeDelegate.onTouch(event);
            }
        });
    }

    @Override
    public void swipeListener(final SwipeDelegate.Listener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public void listenForChildGestures(View child, boolean listenForChildGestures) {
        if (swipeDelegate != null) {
            swipeDelegate.listenForGestures(listenForChildGestures);
        }
    }

    @Override
    protected void onChildAdded(View child) {
        swipeDelegate = new SwipeDelegate(child, this, new SwipeDelegate.Listener() {
            @Override
            public void onViewEscapedLeft(View child) {
                if (swipeListener != null) {
                    swipeListener.onViewEscapedLeft(child);
                }
            }

            @Override
            public void onViewEscapedRight(View child) {
                if (swipeListener != null) {
                    swipeListener.onViewEscapedRight(child);
                }
            }

            @Override
            public void onCardMoved(float percentageOffsetFromCentreX) {
                if (swipeListener != null) {
                    swipeListener.onCardMoved(percentageOffsetFromCentreX);
                }
            }
        });
    }

    @Override
    protected void onChildRemoved(View child) {
        swipeDelegate = null;
    }
}
