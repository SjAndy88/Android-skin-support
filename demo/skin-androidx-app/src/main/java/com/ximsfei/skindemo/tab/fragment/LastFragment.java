package com.ximsfei.skindemo.tab.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ximsfei.skindemo.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class LastFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        EditText mEdit = view.findViewById(R.id.edit);
        mEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.drawable_left_selector,
                R.drawable.drawable_top_selector,
                R.drawable.drawable_right_selector,
                R.drawable.drawable_bottom_selector);
        EditText mEdit1 = view.findViewById(R.id.edit1);
        mEdit1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.drawable_left_selector,
                R.drawable.drawable_top_selector,
                R.drawable.drawable_right_selector,
                R.drawable.drawable_bottom_selector);
        TextView mText1 = view.findViewById(R.id.text1);
        Context context = getActivity();
        if (context != null) {
            mText1.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(context, R.drawable.drawable_left_selector),
                    ContextCompat.getDrawable(context, R.drawable.drawable_top_selector),
                    ContextCompat.getDrawable(context, R.drawable.drawable_right_selector),
                    ContextCompat.getDrawable(context, R.drawable.drawable_bottom_selector));
        }
        return view;
    }
}
