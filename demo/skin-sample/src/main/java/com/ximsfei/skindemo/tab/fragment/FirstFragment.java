package com.ximsfei.skindemo.tab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ximsfei.skindemo.R;



public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        view.findViewById(R.id.image_button).setOnClickListener(v -> Toast.makeText(getActivity(), "Image Button", Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.checked_text_view).setOnClickListener(v -> {
            // TODO Auto-generated method stub
            CheckedTextView checkedTextView = (CheckedTextView) v;
            checkedTextView.toggle();
//                checkedMap.put(position, checkedTextView.isChecked());
        });
        MultiAutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.auto);
        String[] arr = {"aa", "aab", "aac"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        return view;
    }
}
