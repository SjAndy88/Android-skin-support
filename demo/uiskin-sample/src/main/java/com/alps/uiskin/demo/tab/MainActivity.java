package com.alps.uiskin.demo.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alps.uiskin.annotation.Skinable;
import com.alps.uiskin.demo.BaseActivity;
import com.alps.uiskin.demo.settings.SettingsActivity;
import com.alps.uiskin.demo.tab.fragment.FirstFragment;
import com.alps.uiskin.demo.tab.fragment.LastFragment;
import com.alps.uiskin.demo.tab.fragment.SFragment;
import com.alps.uiskin.demo.tab.fragment.TFragment;
import com.alps.uiskin.demo.tab.fragment.TabFragmentPagerAdapter;
import com.alps.uiskin.widget.SkinCompatSupportable;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import uiskin.demo.R;



@Skinable
public class MainActivity extends BaseActivity implements SkinCompatSupportable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        configFragments();
        findViewById(R.id.fab).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SFragment());
        list.add(new TFragment());
        list.add(new LastFragment());
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), list));
        List<String> listTitle = new ArrayList<>();
        listTitle.add("系统组件");
        listTitle.add("自定义View");
        listTitle.add("List");
        listTitle.add("第三方库控件");
        TabFragmentPagerAdapter mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list, listTitle);
        viewPager.setAdapter(mTabFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void applySkin() {
    }
}
