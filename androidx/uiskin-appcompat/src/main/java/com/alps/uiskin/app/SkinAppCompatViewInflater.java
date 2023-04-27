package com.alps.uiskin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.alps.uiskin.content.res.SkinCompatVectorResources;
import com.alps.uiskin.widget.SkinCompatAutoCompleteTextView;
import com.alps.uiskin.widget.SkinCompatButton;
import com.alps.uiskin.widget.SkinCompatCheckBox;
import com.alps.uiskin.widget.SkinCompatCheckedTextView;
import com.alps.uiskin.widget.SkinCompatEditText;
import com.alps.uiskin.widget.SkinCompatFrameLayout;
import com.alps.uiskin.widget.SkinCompatImageButton;
import com.alps.uiskin.widget.SkinCompatImageView;
import com.alps.uiskin.widget.SkinCompatLinearLayout;
import com.alps.uiskin.widget.SkinCompatMultiAutoCompleteTextView;
import com.alps.uiskin.widget.SkinCompatProgressBar;
import com.alps.uiskin.widget.SkinCompatRadioButton;
import com.alps.uiskin.widget.SkinCompatRadioGroup;
import com.alps.uiskin.widget.SkinCompatRatingBar;
import com.alps.uiskin.widget.SkinCompatRelativeLayout;
import com.alps.uiskin.widget.SkinCompatScrollView;
import com.alps.uiskin.widget.SkinCompatSeekBar;
import com.alps.uiskin.widget.SkinCompatSpinner;
import com.alps.uiskin.widget.SkinCompatTextView;
import com.alps.uiskin.widget.SkinCompatToolbar;
import com.alps.uiskin.widget.SkinCompatView;

public class SkinAppCompatViewInflater implements SkinLayoutInflater {

    public SkinAppCompatViewInflater() {
        SkinCompatVectorResources.getInstance();
    }

    @Override
    public View createView(Context context, String name, AttributeSet attrs) {
        View view = createViewFromFV(context, name, attrs);

        if (view == null) {
            view = createViewFromV7(context, name, attrs);
        }
        return view;
    }

    private View createViewFromFV(Context context, String name, AttributeSet attrs) {
        View view = null;
        if (name.contains(".")) {
            return null;
        }
        switch (name) {
            case "View":
                view = new SkinCompatView(context, attrs);
                break;
            case "LinearLayout":
                view = new SkinCompatLinearLayout(context, attrs);
                break;
            case "RelativeLayout":
                view = new SkinCompatRelativeLayout(context, attrs);
                break;
            case "FrameLayout":
                view = new SkinCompatFrameLayout(context, attrs);
                break;
            case "TextView":
                view = new SkinCompatTextView(context, attrs);
                break;
            case "ImageView":
                view = new SkinCompatImageView(context, attrs);
                break;
            case "Button":
                view = new SkinCompatButton(context, attrs);
                break;
            case "EditText":
                view = new SkinCompatEditText(context, attrs);
                break;
            case "Spinner":
                view = new SkinCompatSpinner(context, attrs);
                break;
            case "ImageButton":
                view = new SkinCompatImageButton(context, attrs);
                break;
            case "CheckBox":
                view = new SkinCompatCheckBox(context, attrs);
                break;
            case "RadioButton":
                view = new SkinCompatRadioButton(context, attrs);
                break;
            case "RadioGroup":
                view = new SkinCompatRadioGroup(context, attrs);
                break;
            case "CheckedTextView":
                view = new SkinCompatCheckedTextView(context, attrs);
                break;
            case "AutoCompleteTextView":
                view = new SkinCompatAutoCompleteTextView(context, attrs);
                break;
            case "MultiAutoCompleteTextView":
                view = new SkinCompatMultiAutoCompleteTextView(context, attrs);
                break;
            case "RatingBar":
                view = new SkinCompatRatingBar(context, attrs);
                break;
            case "SeekBar":
                view = new SkinCompatSeekBar(context, attrs);
                break;
            case "ProgressBar":
                view = new SkinCompatProgressBar(context, attrs);
                break;
            case "ScrollView":
                view = new SkinCompatScrollView(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }

    private View createViewFromV7(Context context, String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "androidx.appcompat.widget.Toolbar":
                view = new SkinCompatToolbar(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }

}
