package com.alps.uiskin.demo.loader;

import android.content.Context;

import com.alps.uiskin.load.SkinSDCardLoader;
import com.alps.uiskin.utils.SkinFileUtils;

import java.io.File;

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
