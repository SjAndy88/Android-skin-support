package com.alps.uiskin.widget;


import com.alps.uiskin.SkinCompatManager;


public abstract class SkinCompatHelper {
    protected static final String SYSTEM_ID_PREFIX = "1";
    public static final int INVALID_ID = 0;

    /**
     * check当前是否是系统或androidx的资源，则认为不可以换肤
     * @param resId
     * @return false，不可换肤，true，可以换肤
     */
    static public int checkResourceId(int resId) {
        if (resId == INVALID_ID) {
            return INVALID_ID;
        }
        String hexResId = Integer.toHexString(resId);
        if (hexResId.startsWith(SYSTEM_ID_PREFIX)) {
            return INVALID_ID;
        }
        String entryName = SkinCompatManager.getInstance().getContext().getResources().getResourceEntryName(resId);
        if (entryName.startsWith("abc_") ||
                entryName.startsWith("m3_") ||
                entryName.startsWith("mtrl_") ||
                entryName.startsWith("design_") ||
                entryName.startsWith("material_")) {
            return INVALID_ID;
        }
        return resId;
    }

    abstract public void applySkin();
}
