package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;

public abstract class SwipeLayout extends FrameLayout {

    public SwipeLayout(Context context) {
        super(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void swipeListener(final SwipeDelegate.Listener swipeListener);

    public abstract void listenForChildGestures(final View child, final boolean listenForChildGestures);

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        onChildAdded(child);
    }

    protected abstract void onChildAdded(final View child);

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        onChildRemoved(child);
    }

    protected abstract void onChildRemoved(final View child);
}
