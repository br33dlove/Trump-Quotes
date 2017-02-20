package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.davidcryer.trumpquotes.android.view.ui.helpers.ViewHelper;
import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiChildSwipeLayout extends SwipeLayout {
    private final Map<View, SwipeDelegate> swipeDelegates = new HashMap<>();
    private SwipeDelegate.Listener swipeListener;

    public MultiChildSwipeLayout(Context context) {
        super(context);
        init();
    }

    public MultiChildSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final List<View> viewsHighestToLowest = viewsHighestToLowest();
                for (final View view : viewsHighestToLowest) {
                    if (ViewHelper.coordinatesAreInView(event.getX(), event.getY(), view)) {
                        final SwipeDelegate swipeDelegate = swipeDelegates.get(view);
                        if (swipeDelegate.onTouch(event)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }

    private List<View> viewsHighestToLowest() {
        final List<View> viewsHighestToLowest = new ArrayList<>(swipeDelegates.keySet());
        Collections.sort(viewsHighestToLowest, new Comparator<View>() {
            @Override
            public int compare(View o1, View o2) {
                final float dz = o1.getZ() - o2.getZ();
                if (dz == 0) {
                    return 0;
                }
                return dz > 0 ? 1 : -1;
            }
        });
        return viewsHighestToLowest;
    }

    @Override
    public void swipeListener(final SwipeDelegate.Listener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public void listenForChildGestures(final View child, final boolean listenForChildGestures) {
        final SwipeDelegate swipeDelegate = swipeDelegates.get(child);
        if (swipeDelegate != null) {
            swipeDelegate.listenForGestures(listenForChildGestures);
        }
    }

    @Override
    protected void onChildAdded(View child) {
        swipeDelegates.put(child, new SwipeDelegate(child, this, new SwipeDelegate.Listener() {
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
        }));
    }

    @Override
    protected void onChildRemoved(View child) {
        swipeDelegates.remove(child);
    }
}
