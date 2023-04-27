# Android-skin-support

* [介绍](#介绍)
  * [功能](#功能)
  * [目录结构](#目录结构)
* [框架用法](#用法)
  * [导入](#导入)
  * [使用](#使用)
    * [初始化](#在application的oncreate中初始化)
    * [皮肤开关](#皮肤开关)
    * [加载插件皮肤库](#加载插件皮肤库)
    * [自定义view换肤](#自定义view换肤)
  * [应用内换肤](#应用内换肤)
  * [插件式换肤](#插件式换肤)
    * [新建皮肤工程](#新建android-application工程)
    * [添加皮肤资源](#将需要换肤的资源放到res目录下同名资源)
    * [生成皮肤插件](#打包生成apk-即为皮肤包)
    * [加载皮肤插件](#加载皮肤插件)
  * [自定义加载策略](#自定义加载策略)
    * [自定义sdcard路径](#自定义sdcard路径)
  * [获取当前使用皮肤](#获取当前使用皮肤)
* [缺点](#缺点)

## 介绍

Android-skin-support: 一款 Android 换肤框架, 极低的学习成本, 极好的用户体验.

只需要一行代码, 就可以实现换肤, 你值得拥有!!!

```java
SkinCompatManager.withoutActivity(this).loadSkin();
```

就这么简单, 你的APK已经拥有了强大的换肤功能, 当然现在是拥有了换肤功能, 别忘了[制作皮肤包](#应用内换肤).

### 功能

* [x] 支持布局中用到的资源换肤。
* [x] 支持代码中设置的资源换肤。
* [x] 默认支持大部分基础控件，Material Design换肤。
* [x] 支持多种加载策略([应用内](#应用内换肤)/[插件式](#插件式换肤)/[自定义sdcard路径](#自定义加载策略))。
* [x] 资源加载优先级: 加载策略中的资源-插件式换肤/应用内换肤-应用资源。
* [x] 支持定制化，选择需要的模块加载。
* [x] 支持矢量图(vector/svg)换肤。
* [x] 支持AndroidX

### 目录结构

> [demo](demo)  // 换肤demo 集合

>> [skin-sample](demo/skin-sample)  // demo app

>> [skin-night](demo/skin-night)  // 夜间模式皮肤工程

> [androidx](androidx)   // Android 原生控件

>> [skin-base](androidx／skin-base)  // 换肤框架

>> [skin-appcompat](androidx／skin-appcompat)  // 换肤框架, 基础控件支持

>> [skin-cardview](androidx／skin-cardview)  // 换肤框架, CardView 支持

>> [skin-material](androidx／skin-material)  // 换肤框架, Material Design 支持

>> [skin-constraintlayout](androidx／skin-constraintlayout)  // 换肤框架, ConstraintLayout 支持

> [third-part](third-part)  // 第三方控件换肤支持

>> [circleimageview](third-part／circleimageview)  // hdodenhof/CircleImageView支持

## 用法

[最新版本选择, 请查看更新日志](docs/ChangeLog.md)

### 导入:


#### AndroidX support:

如果项目中使用了[AndroidX](https://developer.android.google.cn/topic/libraries/support-library/androidx-overview), 添加以下依赖
```xml
implementation 'ui.skin:skin-base:0.0.1'               // skin-base
implementation 'ui.skin:skin-appcompat:0.0.1'          // skin-appcompat 基础控件支持
implementation 'ui.skin:skin-material:0.0.1'           // skin-material MaterialDesign 控件支持[可选]
implementation 'ui.skin:skin-cardview:0.0.1'           // skin-cardview CardView 控件支持[可选]
implementation 'ui.skin:skin-constraintlayout:0.0.1'   // skin-constraintlayout ConstraintLayout 控件支持[可选]
```

在Application的onCreate中初始化
    
```java
@Override
public void onCreate() {
    super.onCreate();
    SkinCompatManager.withoutActivity(this)
            .addInflater(new SkinAppCompatViewInflater())           // 基础控件换肤初始化
            .addInflater(new SkinMaterialViewInflater())            // MaterialDesign 控件换肤初始化[可选]
            .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
            .addInflater(new SkinCardViewInflater())                // CardView 控件换肤初始化[可选]
            .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
            .loadSkin();
}
```

> 如果项目中使用的Activity继承自AppCompatActivity，需要重载getDelegate()方法

```java
@NonNull
@Override
public AppCompatDelegate getDelegate() {
    return SkinAppCompatDelegateImpl.get(this, this);
}
```

### 使用:

#### 皮肤开关

如果项目中有特殊需求。例如, 股票控件: 控件颜色始终为红色或绿色, 不需要随着模式切换而换肤

那么可以使用类似的方法, 直接设置drawable
```xml
setBackgroundDrawable(redDrawable) // 不支持换肤
background="#ce3d3a"
```
而不是使用R.drawable.red
```xml
setBackgroundResource(R.drawable.red)
background="@drawable/red"
```

#### 加载插件皮肤库

```java
// 指定皮肤插件
SkinCompatManager.getInstance().loadSkin("new.skin"[, SkinLoaderListener], int strategy);

// 恢复应用默认皮肤
SkinCompatManager.getInstance().restoreDefaultTheme();
```

#### 自定义View换肤

要点:

1. 实现SkinCompatSupportable接口

  1. applySkin方法中实现换肤操作

2. 在构造方法中解析出需要换肤的resId

* 自定义View可以直接继承自SkinCompatView, SkinCompatLinearLayout等已有控件

  eg: [CustomTextView](demo/skin-sample/src/main/java/com/skin/demo/widget/CustomTextView.java)

* 不想继承自已有控件

  eg: [CustomTextView2](demo/skin-sample/src/main/java/com/skin/demo/widget/CustomTextView2.java)

* 需要换肤自定义属性

  // 需要换肤AutoCompleteTextView的R.attr.popupBackground属性

  eg: [SkinCompatAutoCompleteTextView](androidx/skin-appcompat/src/main/java/alps/uiskin/widget/SkinCompatAutoCompleteTextView.java)

* 需要使用第三方库控件怎么办

  // 需要使用https://github.com/hdodenhof/CircleImageView 控件, 并且要支持换肤

  eg: [SkinCompatCircleImageView](third-part/circleimageview/src/main/java/alps/uiskin/circleimageview/widget/SkinCompatCircleImageView.java)

### 应用内换肤:

应用内换肤，皮肤名为: night; 新增需要换肤的资源添加后缀或者前缀。

需要换肤的资源为R.color.windowBackgroundColor, 添加对应资源R.color.windowBackgroundColor_night。

加载应用内皮肤:
```java
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // 后缀加载
```

推荐将应用内换肤相关的皮肤资源放到单独的目录中

eg: [res-night](demo/skin-sample/src/main/res-night)

*注: 如果使用这种方式来增加换肤资源，记得在build.gradle 中配置一下这个资源目录 sourceSets {main {res.srcDirs = ['src/main/res', 'src/main/res-night']}}*


### 插件式换肤:

#### 新建Android application工程

皮肤工程包名不能和宿主应用包名相同.

例如:
```xml
宿主包名: uiskin.demo
夜间模式: uiskin.demo.night
```

#### 将需要换肤的资源放到res目录下(同名资源)

例如 APK中窗口背景颜色为

colors.xml
```xml
<color name="background">#ffffff</color>
```
那么夜间模式你可以在skin-night工程中设置

colors.xml
```xml
<color name="background">#000000</color>
```

#### 打包生成apk, 即为皮肤包

将打包生成的apk文件, 重命名为'xxx.skin', 防止apk结尾的文件造成混淆.

#### 加载皮肤插件

加载插件式皮肤, 将皮肤包放到assets/skins目录下
```java
SkinCompatManager.getInstance().loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
```

### 自定义加载策略:

#### 自定义sdcard路径

继承自`SkinSDCardLoader`，通过`getSkinPath`方法指定皮肤加载路径，通过`getType`方法指定加载器type。

```java
public class CustomSDCardLoader extends SkinSDCardLoader {
    public static final int SKIN_LOADER_STRATEGY_SDCARD = Integer.MAX_VALUE;

    @Override
    protected String getSkinPath(Context context, String skinName) {
        return new File(SkinFileUtils.getSkinDir(context), skinName).getAbsolutePath();
    }

    @Override
    public int getType() {
        return SKIN_LOADER_STRATEGY_SDCARD;
    }
}
```

*注: 自定义加载器type 值最好从整数最大值开始递减，框架的type值从小数开始递增，以免将来框架升级造成type 值冲突*

在Application中，添加自定义加载策略:

```java
SkinCompatManager.withoutActivity(this)
        .addStrategy(new CustomSDCardLoader());          // 自定义加载策略，指定SDCard路径
```

*注: 自定义加载器必须在Application中注册，皮肤切换后，重启应用需要根据当前策略加载皮肤*

使用自定义加载器加载皮肤:

```java
SkinCompatManager.getInstance().loadSkin("night.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
```

*资源加载优先级: 加载策略中的资源-插件式换肤/应用内换肤-应用资源。*

### 获取当前使用皮肤

[SkinPreference](androidx/skin-base/src/main/java/alps/uiskin/utils/SkinPreference.java)

## 缺点

* 同一个LayoutInflater只能设置一次Factory，容易和同类库产生冲突
