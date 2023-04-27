package com.alps.uiskin.demo;

import android.app.Application;

import com.alps.uiskin.SkinCompatManager;
import com.alps.uiskin.app.SkinAppCompatViewInflater;
import com.alps.uiskin.app.SkinCardViewInflater;
import com.alps.uiskin.circleimageview.app.SkinCircleImageViewInflater;
import com.alps.uiskin.constraint.app.SkinConstraintViewInflater;
import com.alps.uiskin.demo.loader.CustomSDCardLoader;
import com.alps.uiskin.design.app.SkinMaterialViewInflater;
import com.alps.uiskin.utils.Slog;

import uiskin.demo.BuildConfig;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 框架换肤日志打印
        Slog.DEBUG = BuildConfig.DEBUG;
        SkinCompatManager.withoutActivity(this)
                .addStrategy(new CustomSDCardLoader())          // 自定义加载策略，指定SDCard路径
                .addInflater(new SkinAppCompatViewInflater())   // 基础控件换肤
                .addInflater(new SkinMaterialViewInflater())    // material design
                .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(new SkinCardViewInflater())        // CardView v7
                .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
//                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
//                .setSkinAllActivityEnable(false)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
                .loadSkin();
    }
}
