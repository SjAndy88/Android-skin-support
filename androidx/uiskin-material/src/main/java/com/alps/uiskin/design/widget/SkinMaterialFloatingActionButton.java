package com.alps.uiskin.design.widget;

import static com.alps.uiskin.widget.SkinCompatHelper.INVALID_ID;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.alps.uiskin.content.res.SkinCompatResources;
import com.alps.uiskin.widget.SkinCompatHelper;
import com.alps.uiskin.widget.SkinCompatImageHelper;
import com.alps.uiskin.widget.SkinCompatSupportable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class SkinMaterialFloatingActionButton extends FloatingActionButton implements SkinCompatSupportable {

    private final SkinCompatImageHelper mImageHelper;

    private int mRippleColorResId;
    private int mBackgroundTintResId;

    public SkinMaterialFloatingActionButton(Context context) {
        this(context, null);
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,
                com.google.android.material.R.styleable.FloatingActionButton, defStyleAttr,
                com.google.android.material.R.style.Widget_Design_FloatingActionButton);
        mBackgroundTintResId = a.getResourceId(com.google.android.material.R.styleable.FloatingActionButton_backgroundTint, INVALID_ID);
        mRippleColorResId = a.getResourceId(com.google.android.material.R.styleable.FloatingActionButton_rippleColor, INVALID_ID);
        a.recycle();
        applyBackgroundTintResource();
        applyRippleColorResource();

        mImageHelper = new SkinCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    private void applyBackgroundTintResource() {
        mBackgroundTintResId = SkinCompatHelper.checkResourceId(mBackgroundTintResId);
        if (mBackgroundTintResId != INVALID_ID) {
            setBackgroundTintList(SkinCompatResources.getColorStateList(getContext(), mBackgroundTintResId));
        }
    }

    private void applyRippleColorResource() {
        mRippleColorResId = SkinCompatHelper.checkResourceId(mRippleColorResId);
        if (mRippleColorResId != INVALID_ID) {
            setRippleColor(SkinCompatResources.getColor(getContext(), mRippleColorResId));
        }
    }

    @Override
    public void applySkin() {
        applyBackgroundTintResource();
        applyRippleColorResource();
        if (mImageHelper != null) {
            mImageHelper.applySkin();
        }
    }

}
