package com.alps.uiskin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.alps.uiskin.SkinCompatManager;
import com.alps.uiskin.annotation.NonNull;
import com.alps.uiskin.widget.SkinCompatSupportable;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class SkinCompatDelegate implements LayoutInflater.Factory2 {
    private SkinCompatViewInflater mSkinCompatViewInflater;
    private final List<WeakReference<SkinCompatSupportable>> mSkinHelpers = new CopyOnWriteArrayList<>();

    private SkinCompatDelegate() {
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(parent, name, context, attrs);

        if (view == null) {
            return null;
        }
        if (view instanceof SkinCompatSupportable) {
            mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }

        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = createView(null, name, context, attrs);

        if (view == null) {
            return null;
        }
        if (view instanceof SkinCompatSupportable) {
            mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }

        return view;
    }

    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        if (mSkinCompatViewInflater == null) {
            mSkinCompatViewInflater = new SkinCompatViewInflater();
        }

        List<SkinWrapper> wrapperList = SkinCompatManager.getInstance().getWrappers();
        for (SkinWrapper wrapper : wrapperList) {
            Context wrappedContext = wrapper.wrapContext(context, parent, attrs);
            if (wrappedContext != null) {
                context = wrappedContext;
            }
        }
        return mSkinCompatViewInflater.createView(parent, name, context, attrs);
    }

    public static SkinCompatDelegate create() {
        return new SkinCompatDelegate();
    }

    public void onLowMemory() {
        Iterator<WeakReference<SkinCompatSupportable>> iterator = mSkinHelpers.iterator();
        while (iterator.hasNext()) {
            SkinCompatSupportable next = iterator.next().get();
            if (next == null) {
                iterator.remove();
            }
        }
    }

    public void applySkin() {
        if (!mSkinHelpers.isEmpty()) {
            for (WeakReference<SkinCompatSupportable> ref : mSkinHelpers) {
                if (ref != null && ref.get() != null) {
                    ref.get().applySkin();
                }
            }
        }
    }
}
