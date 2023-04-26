package com.skin.demo;

import android.app.Application;

import com.skin.demo.loader.CustomSDCardLoader;

import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;
import skin.support.app.SkinCardViewInflater;
import skin.support.circleimageview.app.SkinCircleImageViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.utils.Slog;



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
