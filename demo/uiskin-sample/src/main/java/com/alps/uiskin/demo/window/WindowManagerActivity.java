package com.alps.uiskin.demo.window;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alps.uiskin.demo.BaseActivity;

import uiskin.demo.R;

public class WindowManagerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manager);
        initToolbar();
        findViewById(R.id.button).setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(WindowManagerActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
                return;
            }
            startWindowService();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (Settings.canDrawOverlays(this)) {
                startWindowService();
            } else {
                Toast.makeText(WindowManagerActivity.this, "not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startWindowService() {
        Intent intent = new Intent();
        intent.setClass(WindowManagerActivity.this, WindowService.class);
        startService(intent);
    }
}
