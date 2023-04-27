package com.alps.uiskin.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.widget.TextViewCompat;

import com.alps.uiskin.appcompat.R;
import com.alps.uiskin.content.res.SkinCompatResources;
import com.alps.uiskin.content.res.SkinCompatVectorResources;


public class SkinCompatTextHelperV17 extends SkinCompatTextHelper {
    private int mDrawableStartResId = INVALID_ID;
    private int mDrawableEndResId = INVALID_ID;

    public SkinCompatTextHelperV17(TextView view) {
        super(view);
    }

    @Override
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SkinCompatTextHelper,
                defStyleAttr, 0);
        if (a.hasValue(R.styleable.SkinCompatTextHelper_drawableStart)) {
            mDrawableStartResId = a.getResourceId(R.styleable.SkinCompatTextHelper_drawableStart, INVALID_ID);
            mDrawableStartResId = SkinCompatHelper.checkResourceId(mDrawableStartResId);
        }
        if (a.hasValue(R.styleable.SkinCompatTextHelper_drawableStartCompat)) {
            mDrawableStartResId = a.getResourceId(R.styleable.SkinCompatTextHelper_drawableStartCompat, INVALID_ID);
            mDrawableStartResId = SkinCompatHelper.checkResourceId(mDrawableStartResId);
        }
        if (a.hasValue(R.styleable.SkinCompatTextHelper_drawableEnd)) {
            mDrawableEndResId = a.getResourceId(R.styleable.SkinCompatTextHelper_drawableEnd, INVALID_ID);
            mDrawableEndResId = SkinCompatHelper.checkResourceId(mDrawableEndResId);
        }
        if (a.hasValue(R.styleable.SkinCompatTextHelper_drawableEndCompat)) {
            mDrawableEndResId = a.getResourceId(R.styleable.SkinCompatTextHelper_drawableEndCompat, INVALID_ID);
            mDrawableEndResId = SkinCompatHelper.checkResourceId(mDrawableEndResId);
        }
        a.recycle();
        super.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void onSetCompoundDrawablesRelativeWithIntrinsicBounds(
            @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
        mDrawableStartResId = start;
        mDrawableTopResId = top;
        mDrawableEndResId = end;
        mDrawableBottomResId = bottom;
        applyCompoundDrawablesRelativeResource();
    }

    @Override
    protected void applyCompoundDrawablesRelativeResource() {
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null,
                drawableStart = null, drawableEnd = null;
        mDrawableLeftResId = checkResourceId(mDrawableLeftResId);
        if (mDrawableLeftResId != INVALID_ID) {
            drawableLeft = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mDrawableLeftResId);
        }
        mDrawableTopResId = checkResourceId(mDrawableTopResId);
        if (mDrawableTopResId != INVALID_ID) {
            drawableTop = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mDrawableTopResId);
        }
        mDrawableRightResId = checkResourceId(mDrawableRightResId);
        if (mDrawableRightResId != INVALID_ID) {
            drawableRight = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mDrawableRightResId);
        }
        mDrawableBottomResId = checkResourceId(mDrawableBottomResId);
        if (mDrawableBottomResId != INVALID_ID) {
            drawableBottom = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mDrawableBottomResId);
        }
        if (mDrawableStartResId != INVALID_ID) {
            drawableStart = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mDrawableStartResId);
        }
        if (drawableStart == null) {
            drawableStart = drawableLeft;
        }
        if (mDrawableEndResId != INVALID_ID) {
            drawableEnd = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mDrawableEndResId);
        }
        if (drawableEnd == null) {
            drawableEnd = drawableRight;
        }
        if (mDrawableLeftResId != INVALID_ID
                || mDrawableTopResId != INVALID_ID
                || mDrawableRightResId != INVALID_ID
                || mDrawableBottomResId != INVALID_ID
                || mDrawableStartResId != INVALID_ID
                || mDrawableEndResId != INVALID_ID) {
            mView.setCompoundDrawablesWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom);
        }
        mDrawableTintResId = checkResourceId(mDrawableTintResId);
        if (mDrawableTintResId != INVALID_ID) {
            ColorStateList tintList = SkinCompatResources.getColorStateList(mView.getContext(), mDrawableTintResId);
            TextViewCompat.setCompoundDrawableTintList(mView, tintList);
        }
    }
}
