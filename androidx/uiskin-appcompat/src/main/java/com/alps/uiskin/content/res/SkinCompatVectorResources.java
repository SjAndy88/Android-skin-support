package com.alps.uiskin.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

public class SkinCompatVectorResources implements SkinResources {
    private static volatile SkinCompatVectorResources sInstance;

    private SkinCompatVectorResources() {
        SkinCompatResources.getInstance().addSkinResources(this);
    }

    public static SkinCompatVectorResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatVectorResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatVectorResources();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void clear() {
    }

    private Drawable getSkinDrawableCompat(Context context, int resId) {
        if (!SkinCompatUserThemeManager.get().isColorEmpty()) {
            ColorStateList colorStateList = SkinCompatUserThemeManager.get().getColorStateList(resId);
            if (colorStateList != null) {
                return new ColorDrawable(colorStateList.getDefaultColor());
            }
        }
        if (!SkinCompatUserThemeManager.get().isDrawableEmpty()) {
            Drawable drawable = SkinCompatUserThemeManager.get().getDrawable(resId);
            if (drawable != null) {
                return drawable;
            }
        }
        Drawable drawable = SkinCompatResources.getInstance().getStrategyDrawable(context, resId);
        if (drawable != null) {
            return drawable;
        }
        if (!SkinCompatResources.getInstance().isDefaultSkin()) {
            int targetResId = SkinCompatResources.getInstance().getTargetResId(context, resId);
            if (targetResId != 0) {
                Resources skinResources = SkinCompatResources.getInstance().getSkinResources();
                return ResourcesCompat.getDrawable(skinResources, targetResId, context.getTheme());
            }
        }
        return AppCompatResources.getDrawable(context, resId);
    }

    public static Drawable getDrawableCompat(Context context, int resId) {
        return getInstance().getSkinDrawableCompat(context, resId);
    }
}
