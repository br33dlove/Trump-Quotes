package com.davidcryer.trumpquotes.android.helpers;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

public class DrawableHelper {

    public static Drawable drawable(final Context context, @DrawableRes final int drawableRes, @ColorRes final int colorRes) {
        final Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        final int color = ContextCompat.getColor(context, colorRes);
        changeDrawableColor(drawable, color);
        return drawable;
    }

    public static void changeDrawableColor(Drawable drawable, final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTint(color);
        }
        else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_OUT);
        }
    }
}
