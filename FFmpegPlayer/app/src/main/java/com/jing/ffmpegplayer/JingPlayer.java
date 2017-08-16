package com.jing.ffmpegplayer;

import android.view.Surface;

/**
 * author: 陈永镜 .
 * date: 2017/8/16 .
 * email: jing20071201@qq.com
 * <p>
 * introduce:
 */
public class JingPlayer {

    static{
        System.loadLibrary("avutil-54");
        System.loadLibrary("swresample-1");
        System.loadLibrary("avcodec-56");
        System.loadLibrary("avformat-56");
        System.loadLibrary("swscale-3");
        System.loadLibrary("postproc-53");
        System.loadLibrary("avfilter-5");
        System.loadLibrary("avdevice-56");
        System.loadLibrary("jing_player");
    }

    public native void render(String input,Surface surface);

}
