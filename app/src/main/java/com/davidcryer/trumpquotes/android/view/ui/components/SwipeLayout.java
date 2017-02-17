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

    public SwipeLayout(Context context) {
        super(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        swipeDelegates.add(new SwipeDelegate(child, new SwipeDelegate.Listener() {}));
    }
}
