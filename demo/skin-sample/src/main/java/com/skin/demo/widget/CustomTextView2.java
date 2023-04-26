package com.skin.demo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatTextView;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;



public class CustomTextView2 extends AppCompatTextView implements SkinCompatSupportable {
    private final SkinCompatTextHelper mTextHelper;
    private final SkinCompatBackgroundHelper mBackgroundTintHelper;

    public CustomTextView2(Context context) {
        this(context, null);
    }

    public CustomTextView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
    }

}
