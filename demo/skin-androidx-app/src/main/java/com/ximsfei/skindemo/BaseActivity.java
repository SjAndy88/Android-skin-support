package com.ximsfei.skindemo;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.ximsfei.skindemo.settings.SettingsActivity;

import skin.support.annotation.Skinable;

/**
 * Created by ximsfei on 17-3-1.
 */

@Skinable
public class BaseActivity extends AppCompatActivity {
    protected void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Subtitle");
        toolbar.setNavigationIcon(R.drawable.ic_settings_black_24dp);
        toolbar.setNavigationOnClickListener(v -> startActivity(new Intent(BaseActivity.this, SettingsActivity.class)));
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_camera_24dp));
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}
