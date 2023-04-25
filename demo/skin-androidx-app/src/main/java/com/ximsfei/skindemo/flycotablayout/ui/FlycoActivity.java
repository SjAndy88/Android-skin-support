package com.ximsfei.skindemo.flycotablayout.ui;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.settings.SettingsActivity;

/**
 * Created by pengfengwang on 2017/3/9.
 */

public class FlycoActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.btn).setOnClickListener(v -> startActivity(new Intent(FlycoActivity.this, SettingsActivity.class)));
    }
}
