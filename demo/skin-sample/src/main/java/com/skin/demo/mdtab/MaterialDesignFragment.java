package com.skin.demo.mdtab;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.skin.demo.R;



public class MaterialDesignFragment extends Fragment {
    private View mView;
    private TextInputLayout textInputLayout;
    private EditText editText2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_md_first, container, false);
        initTextInputLayout();
        initTextInputLayout2();
        mView.findViewById(R.id.CollapsingToolbarLayout).setOnClickListener(v -> startActivity(new Intent(getActivity(), CollapsingToolbarLayoutActivity.class)));
        return mView;
    }

    private void initTextInputLayout2() {
        TextInputLayout textInputLayout2 = mView.findViewById(R.id.textInputLayout2);
        textInputLayout2.setHint("请输入4位学号");
        editText2 = mView.findViewById(R.id.editText2);
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    editText2.setError("学号输入错误");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initTextInputLayout() {
        textInputLayout = mView.findViewById(R.id.textInputLayout);
        textInputLayout.setHint("请输入4位学号");
        EditText editText = mView.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    textInputLayout.setError("学号输入错误");
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
