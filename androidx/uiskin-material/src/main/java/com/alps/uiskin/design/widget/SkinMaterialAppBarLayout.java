package com.alps.uiskin.design.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.alps.uiskin.widget.SkinCompatBackgroundHelper;
import com.alps.uiskin.widget.SkinCompatSupportable;
import com.google.android.material.appbar.AppBarLayout;



public class SkinMaterialAppBarLayout extends AppBarLayout implements SkinCompatSupportable {

    private final SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinMaterialAppBarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, 0);
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

}
