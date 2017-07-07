package com.jing.fmodchangevoice;

/**
 * author: 陈永镜 .
 * date: 2017/6/30 .
 * email: jing20071201@qq.com
 * <p>
 * introduce:
 */
public class EffectUtils {

    public static final int MODE_NORMAL = 0;
    public static final int MODE_LUOLI = 1;
    public static final int MODE_DASHU = 2;
    public static final int MODE_JINGSONG = 3;
    public static final int MODE_GAOGUAI = 4;
    public static final int MODE_KONGLING = 5;

    public native static void fix(String path, int type);


    static {
        System.loadLibrary("effect_fix");
        System.loadLibrary("fmod");
        System.loadLibrary("fmodL");
    }

}
