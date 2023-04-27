package com.alps.uiskin.demo.mdtab;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alps.uiskin.demo.BaseActivity;
import com.alps.uiskin.demo.settings.SettingsActivity;
import com.alps.uiskin.demo.tab.fragment.TabFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import uiskin.demo.R;



public class MaterialDesignActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        initToolbar();
        configFragments();
        findViewById(R.id.fab).setOnClickListener(v -> startActivity(new Intent(MaterialDesignActivity.this, SettingsActivity.class)));
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new MaterialDesignFragment());
//        list.add(new SFragment());
//        list.add(new LastFragment());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<String> listTitle = new ArrayList<>();
        listTitle.add("系统组件");
//        listTitle.add("自定义View");
//        listTitle.add("第三方库控件");
        TabFragmentPagerAdapter mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list, listTitle);
        viewPager.setAdapter(mTabFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
