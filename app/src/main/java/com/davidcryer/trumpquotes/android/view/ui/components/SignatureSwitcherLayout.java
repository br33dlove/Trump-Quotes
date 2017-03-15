package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.davidcryer.trumpquotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignatureSwitcherLayout extends FrameLayout {
    private final static float PERCENTAGE_OFFSET_FOR_MAX_ALPHA = 0.25f;
    @BindView(R.id.quote_signature_left)
    TextView leftSignatureView;
    @BindView(R.id.quote_signature_right)
    TextView rightSignatureView;

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

    public void showSignatures(final String leftSignature, final String rightSignature) {
        final String dashFormat = getResources().getString(R.string.signature_format_dash);
        leftSignatureView.setText(String.format(dashFormat, leftSignature));
        rightSignatureView.setText(String.format(dashFormat, rightSignature));
    }

    public void update(final float percentageOffsetFromCentreX) {
        leftSignatureView.setAlpha(leftSignatureAlpha(percentageOffsetFromCentreX));
        rightSignatureView.setAlpha(rightSignatureAlpha(percentageOffsetFromCentreX));
    }

    private float leftSignatureAlpha(final float percentageOffsetFromCentre) {
        return percentageOffsetFromCentre < 0 ? alpha(percentageOffsetFromCentre) : 0f;
    }

    private float rightSignatureAlpha(final float percentageOffsetFromCentre) {
        return percentageOffsetFromCentre > 0 ? alpha(percentageOffsetFromCentre) : 0f;
    }

    private float alpha(final float percentageOffsetFromCentre) {
        return Math.min(Math.abs(percentageOffsetFromCentre) / PERCENTAGE_OFFSET_FOR_MAX_ALPHA, 1);
    }
}
