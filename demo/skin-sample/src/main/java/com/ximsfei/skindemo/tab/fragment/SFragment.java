package com.ximsfei.skindemo.tab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ximsfei.skindemo.R;



public class SFragment extends Fragment {
    private ProgressBar mHorizontalBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_middle, container, false);
        mHorizontalBar = view.findViewById(R.id.progress_horizontal);
        Button mAdd = view.findViewById(R.id.add);
        mAdd.setOnClickListener(view1 -> mHorizontalBar.setProgress(mHorizontalBar.getProgress() + 2));
        Spinner mSpinner = view.findViewById(R.id.spinner);
        final CharSequence[] entries = getResources().getStringArray(R.array.languages);
        if (entries != null) {
            final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), R.layout.simple_spinner_item, entries);
            adapter.setDropDownViewResource(R.layout.simple_spinner_item);
            mSpinner.setAdapter(adapter);
        }
        return view;
    }
}
