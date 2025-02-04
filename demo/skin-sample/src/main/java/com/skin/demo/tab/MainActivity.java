package com.skin.demo.tab;

import android.os.Bundle;
import android.view.Menu;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.skin.demo.BaseActivity;
import com.skin.demo.R;
import com.skin.demo.tab.fragment.FirstFragment;
import com.skin.demo.tab.fragment.LastFragment;
import com.skin.demo.tab.fragment.SFragment;
import com.skin.demo.tab.fragment.TFragment;
import com.skin.demo.tab.fragment.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import skin.support.annotation.Skinable;
import skin.support.widget.SkinCompatSupportable;



@Skinable
public class MainActivity extends BaseActivity implements SkinCompatSupportable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        configFragments();
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
