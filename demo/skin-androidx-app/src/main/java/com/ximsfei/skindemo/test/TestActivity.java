package com.ximsfei.skindemo.test;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewStub;

import androidx.annotation.Nullable;

import com.ximsfei.skindemo.BaseActivity;
import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.mdtab.MDFirstFragment;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initToolbar();
        ViewStub viewStub = findViewById(R.id.view_stub);
        viewStub.setLayoutResource(R.layout.fragment_view_stub);
        viewStub.inflate();
        MDFirstFragment fragment = (MDFirstFragment) getSupportFragmentManager().findFragmentById(R.id.md_fragment);
        Log.e("TestActivity", "fragment = " + fragment);
    }
}
