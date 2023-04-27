package com.alps.uiskin.constraint.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.alps.uiskin.app.SkinLayoutInflater;
import com.alps.uiskin.constraint.SkinCompatConstraintLayout;

public class SkinConstraintViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(Context context, final String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "androidx.constraintlayout.widget.ConstraintLayout":
                view = new SkinCompatConstraintLayout(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
