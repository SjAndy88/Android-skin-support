package com.alps.uiskin.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alps.uiskin.utils.Slog;

import java.lang.reflect.Field;
import java.util.WeakHashMap;

public final class LayoutInflaterCompat {
    private static final String TAG = "LayoutInflaterCompatHC";

    private static final WeakHashMap<LayoutInflater, Boolean> sCheckedFields = new WeakHashMap<>();

    static class Factory2Wrapper implements LayoutInflater.Factory2 {
        final LayoutInflaterFactory mDelegateFactory;

        Factory2Wrapper(LayoutInflaterFactory delegateFactory) {
            mDelegateFactory = delegateFactory;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return mDelegateFactory.onCreateView(null, name, context, attrs);
        }

        @Override
        public View onCreateView(View parent, String name, Context context,
                                 AttributeSet attributeSet) {
            return mDelegateFactory.onCreateView(parent, name, context, attributeSet);
        }
    }

    /*
     * Hide the constructor.
     */
    private LayoutInflaterCompat() {
    }

    /**
     * Attach a custom Factory interface for creating views while using
     * this LayoutInflater. This must not be null, and can only be set once;
     * after setting, you can not change the factory.
     *
     * @see LayoutInflater#setFactory(android.view.LayoutInflater.Factory)
     * @deprecated Use {@link #setFactory2(LayoutInflater, LayoutInflater.Factory2)} instead to set
     * and {@link LayoutInflater#getFactory2()} to get the factory.
     */
    @Deprecated
    public static void setFactory(
            LayoutInflater inflater, LayoutInflaterFactory factory) {
        inflater.setFactory2(factory != null ? new Factory2Wrapper(factory) : null);
    }

    /**
     * Attach a custom {@link LayoutInflater.Factory2} for creating views while using
     * this {@link LayoutInflater}. This must not be null, and can only be set once;
     * after setting, you can not change the factory.
     *
     * @see LayoutInflater#setFactory2(android.view.LayoutInflater.Factory2)
     */
    public static void setFactory2(LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        try {
            inflater.setFactory2(factory);
        } catch (Exception e) {
            Slog.i("setFactory2 failed. Try forceSetFactory2");
            forceSetFactory2(inflater, factory);
        }
    }

    /**
     * For APIs < 21, there was a framework bug that prevented a LayoutInflater's
     * Factory2 from being merged properly if set after a cloneInContext from a LayoutInflater
     * that already had a Factory2 registered. We work around that bug here. If we can't we
     * log an error.
     */
    @SuppressLint("DiscouragedPrivateApi")
    @SuppressWarnings("JavaReflectionMemberAccess")
    private static void forceSetFactory2(LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        Field factory2Field = null;
        if (!hasChecked(inflater)) {
            try {
                factory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                factory2Field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "forceSetFactory2 Could not find field 'mFactory2' on class "
                        + LayoutInflater.class.getName()
                        + "; inflation may have unexpected results.", e);
            }
            setChecked(inflater);
        }
        if (factory2Field != null) {
            try {
                factory2Field.set(inflater, factory);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "forceSetFactory2 could not set the Factory2 on LayoutInflater "
                        + inflater + "; inflation may have unexpected results.", e);
            }
        }
    }

    /**
     * has checked
     *
     * @param inflater LayoutInflater
     * @return true, has checked, false not check
     */
    private static boolean hasChecked(LayoutInflater inflater) {
        Boolean checked = sCheckedFields.get(inflater);
        return checked != null && checked;
    }

    /**
     * set checked
     *
     * @param inflater LayoutInflater
     */
    private static void setChecked(LayoutInflater inflater) {
        sCheckedFields.put(inflater, true);
    }
}