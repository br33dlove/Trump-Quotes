package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;

import java.util.HashSet;
import java.util.Set;

public class SwipeLayout extends FrameLayout {
    private final Set<SwipeDelegate> swipeDelegates = new HashSet<>();
    private SwipeDelegate.Listener swipeListener;

    public SwipeLayout(Context context) {
        super(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void swipeListener(final SwipeDelegate.Listener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        swipeDelegates.add(new SwipeDelegate(child, this, new SwipeDelegate.Listener() {
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
        }));
    }
}
