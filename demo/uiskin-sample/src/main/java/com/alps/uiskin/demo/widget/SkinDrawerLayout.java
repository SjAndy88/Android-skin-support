package com.alps.uiskin.demo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.drawerlayout.widget.DrawerLayout;

import com.alps.uiskin.widget.SkinCompatBackgroundHelper;
import com.alps.uiskin.widget.SkinCompatSupportable;

public class SkinDrawerLayout extends DrawerLayout implements SkinCompatSupportable {
    private final SkinCompatBackgroundHelper mBackgroundHelper;

    public SkinDrawerLayout(Context context) {
        this(context, null);
    }

    public SkinDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mBackgroundHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundHelper.loadFromAttributes(attrs, defStyle);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    public void applySkin() {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.applySkin();
        }
    }
}
