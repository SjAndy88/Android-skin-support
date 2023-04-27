package com.alps.uiskin.circleimageview.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.alps.uiskin.app.SkinLayoutInflater;
import com.alps.uiskin.circleimageview.widget.SkinCompatCircleImageView;



public class SkinCircleImageViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(Context context, final String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "de.hdodenhof.circleimageview.CircleImageView":
                view = new SkinCompatCircleImageView(context, attrs);
                break;
        }
        return view;
    }
}
