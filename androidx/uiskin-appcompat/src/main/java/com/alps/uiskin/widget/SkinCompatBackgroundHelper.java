package com.alps.uiskin.widget;

import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.view.ViewCompat;

import com.alps.uiskin.appcompat.R;
import com.alps.uiskin.content.res.SkinCompatVectorResources;


public class SkinCompatBackgroundHelper extends SkinCompatHelper {
    private final View mView;

    private int mBackgroundResId = INVALID_ID;
    private int mForegroundResId = INVALID_ID;

    public SkinCompatBackgroundHelper(View view) {
        mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
                mBackgroundResId = a.getResourceId(
                        R.styleable.SkinBackgroundHelper_android_background, INVALID_ID);
            }
            if (a.hasValue(R.styleable.SkinBackgroundHelper_android_foreground)) {
                mForegroundResId = a.getResourceId(
                        R.styleable.SkinBackgroundHelper_android_foreground, INVALID_ID);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    public void onSetBackgroundResource(int resId) {
        mBackgroundResId = resId;
        // Update the default background tint
        applySkin();
    }

    @Override
    public void applySkin() {
        mBackgroundResId = checkResourceId(mBackgroundResId);
        if (mBackgroundResId == INVALID_ID) {
            return;
        }
        ColorFilter bgFilter = null;
        if (mView.getBackground() != null) {
            bgFilter = mView.getBackground().getColorFilter();
        }
        Drawable bgDrawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mBackgroundResId);
        if (bgDrawable != null) {
            int paddingLeft = mView.getPaddingLeft();
            int paddingTop = mView.getPaddingTop();
            int paddingRight = mView.getPaddingRight();
            int paddingBottom = mView.getPaddingBottom();
            ViewCompat.setBackground(mView, bgDrawable);
            mView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
        if (bgFilter != null) {
            mView.getBackground().setColorFilter(bgFilter);
        }

        mForegroundResId = checkResourceId(mForegroundResId);
        if (mForegroundResId == INVALID_ID) {
            return;
        }
        ColorFilter fgFilter = null;
        if (mView.getForeground() != null) {
            fgFilter = mView.getForeground().getColorFilter();
        }
        Drawable fgDrawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mForegroundResId);
        if (fgDrawable != null) {
            mView.setForeground(fgDrawable);
        }
        if (fgFilter != null) {
            mView.getForeground().setColorFilter(fgFilter);
        }
    }
}
