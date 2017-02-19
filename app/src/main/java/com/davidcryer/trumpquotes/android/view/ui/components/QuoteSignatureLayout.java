package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.davidcryer.trumpquotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuoteSignatureLayout extends FrameLayout {
    private final static float PERCENTAGE_OFFSET_FOR_MAX_ALPHA = 50.0f;
    @BindView(R.id.quote_signature_is_trump)
    View isTrumpTextView;
    @BindView(R.id.quote_signature_not_trump)
    View notTrumpTextView;

    public QuoteSignatureLayout(Context context) {
        super(context);
        init();
    }

    public QuoteSignatureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.container_quote_signature_switcher, this);
        ButterKnife.bind(this);
    }

    public void update(final float percentageOffsetFromCentre) {
        isTrumpTextView.setAlpha(isTrumpAlpha(percentageOffsetFromCentre));
        notTrumpTextView.setAlpha(notTrumpAlpha(percentageOffsetFromCentre));
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
