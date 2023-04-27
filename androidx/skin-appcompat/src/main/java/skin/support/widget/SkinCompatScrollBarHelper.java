package skin.support.widget;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import skin.support.R;
import skin.support.content.res.SkinCompatVectorResources;


public class SkinCompatScrollBarHelper extends SkinCompatHelper {
    private final View mView;

    private int mVerticalThumbDrawableResId = INVALID_ID;
    private int mVerticalTrackDrawableResId = INVALID_ID;

    private int mHorizontalThumbDrawableResId = INVALID_ID;
    private int mHorizontalTrackDrawableResId = INVALID_ID;

    public SkinCompatScrollBarHelper(View view) {
        mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinScrollBarHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinScrollBarHelper_android_scrollbarThumbVertical)) {
                mVerticalThumbDrawableResId = a.getResourceId(
                        R.styleable.SkinScrollBarHelper_android_scrollbarThumbVertical, INVALID_ID);
            }
            if (a.hasValue(R.styleable.SkinScrollBarHelper_android_scrollbarTrackVertical)) {
                mVerticalTrackDrawableResId = a.getResourceId(
                        R.styleable.SkinScrollBarHelper_android_scrollbarTrackVertical, INVALID_ID);
            }
            if (a.hasValue(R.styleable.SkinScrollBarHelper_android_scrollbarThumbHorizontal)) {
                mHorizontalThumbDrawableResId = a.getResourceId(
                        R.styleable.SkinScrollBarHelper_android_scrollbarThumbHorizontal, INVALID_ID);
            }
            if (a.hasValue(R.styleable.SkinScrollBarHelper_android_scrollbarTrackHorizontal)) {
                mHorizontalTrackDrawableResId = a.getResourceId(
                        R.styleable.SkinScrollBarHelper_android_scrollbarTrackHorizontal, INVALID_ID);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    @SuppressLint("DiscouragedPrivateApi")
    private void setVerticalScrollBarDrawable(String method, Drawable drawable) {
        Class<?> cls = View.class;
        try {
            Field field = cls.getDeclaredField("mScrollCache");
            field.setAccessible(true);
            Object scrollCache = field.get(mView);
            if (scrollCache != null) {
                Class<?> scrollCacheCls = scrollCache.getClass();
                Field scrollBarField = scrollCacheCls.getDeclaredField("scrollBar");
                Object scrollBar = scrollBarField.get(scrollCache);
                if (scrollBar != null) {
                    Method setMethod = scrollBar.getClass().getDeclaredMethod(method, Drawable.class);
                    setMethod.setAccessible(true);
                    setMethod.invoke(scrollBar, drawable);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setVerticalThumbDrawable() {
        mVerticalThumbDrawableResId = checkResourceId(mVerticalThumbDrawableResId);
        if (mVerticalThumbDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mVerticalThumbDrawableResId);
            setVerticalScrollBarDrawable("setVerticalThumbDrawable", drawable);
        }
    }

    private void setVerticalTrackDrawable() {
        mVerticalTrackDrawableResId = checkResourceId(mVerticalTrackDrawableResId);
        if (mVerticalTrackDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mVerticalTrackDrawableResId);
            setVerticalScrollBarDrawable("setVerticalTrackDrawable", drawable);
        }
    }

    private void setHorizontalThumbDrawable() {
        mHorizontalThumbDrawableResId = checkResourceId(mHorizontalThumbDrawableResId);
        if (mHorizontalThumbDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mHorizontalThumbDrawableResId);
            setVerticalScrollBarDrawable("setHorizontalThumbDrawable", drawable);
        }
    }

    private void setHorizontalTrackDrawable() {
        mHorizontalTrackDrawableResId = checkResourceId(mHorizontalTrackDrawableResId);
        if (mHorizontalTrackDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mHorizontalTrackDrawableResId);
            setVerticalScrollBarDrawable("setHorizontalTrackDrawable", drawable);
        }
    }



    @Override
    public void applySkin() {
        setVerticalThumbDrawable();
        setVerticalTrackDrawable();
        setHorizontalThumbDrawable();
        setHorizontalTrackDrawable();
    }
}
