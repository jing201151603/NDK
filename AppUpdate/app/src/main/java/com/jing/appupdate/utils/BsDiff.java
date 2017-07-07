package com.jing.appupdate.utils;

/**
 * author: 陈永镜 .
 * date: 2017/6/28 .
 * email: jing20071201@qq.com
 * <p>
 * introduce:
 */
public class BsDiff {

    static{
        System.loadLibrary("bsdiff");
    }

    /**
     * 拆分
     * @param oldfile
     * @param newfile
     * @param patchfile
     */
    public native static void diff(String oldfile, String newfile, String patchfile);


}
