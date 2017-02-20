package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.davidcryer.trumpquotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignatureSwitcherLayout extends FrameLayout {
    private final static float PERCENTAGE_OFFSET_FOR_MAX_ALPHA = 0.25f;
    @BindView(R.id.quote_signature_is_trump)
    View isTrumpSignatureView;
    @BindView(R.id.quote_signature_not_trump)
    View notTrumpSignatureView;

    public SignatureSwitcherLayout(Context context) {
        super(context);
        init();
    }

    public SignatureSwitcherLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.container_quote_signature_switcher, this);
        ButterKnife.bind(this);
    }

    public void update(final float percentageOffsetFromCentre) {
        Log.d(SignatureSwitcherLayout.class.getSimpleName(), "offset: " + percentageOffsetFromCentre);
        isTrumpSignatureView.setAlpha(isTrumpAlpha(percentageOffsetFromCentre));
        notTrumpSignatureView.setAlpha(notTrumpAlpha(percentageOffsetFromCentre));
    }

    private float isTrumpAlpha(final float percentageOffsetFromCentre) {
        return percentageOffsetFromCentre < 0 ? alpha(percentageOffsetFromCentre) : 0f;
    }

    private float notTrumpAlpha(final float percentageOffsetFromCentre) {
        return percentageOffsetFromCentre > 0 ? alpha(percentageOffsetFromCentre) : 0f;
    }

    private float alpha(final float percentageOffsetFromCentre) {
        return Math.min(Math.abs(percentageOffsetFromCentre) / PERCENTAGE_OFFSET_FOR_MAX_ALPHA, 1);
    }
}
