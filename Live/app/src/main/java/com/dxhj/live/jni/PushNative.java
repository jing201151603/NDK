package com.dxhj.live.jni;

import com.dxhj.live.listener.OnLiveStateChangeListener;

/**
 * author: 陈永镜 .
 * date: 2017/8/8 .
 * email: jing20071201@qq.com
 * <p>
 * introduce:
 */
public class PushNative {

    public static final int connect_failed = 101;
    public static final int init_failed = 102;

    private OnLiveStateChangeListener onLiveStateChangeListener;

    public void throwNativeError(int code) {
        if (onLiveStateChangeListener != null) {
            onLiveStateChangeListener.onError(code);
        }
    }

    public native void startPush(String url);

    public native void stopPush();

    public native void release();

    /**
     * 设置视频参数
     *
     * @param width
     * @param height
     * @param bitrate
     * @param fps
     */
    public native void setVideoOptions(int width, int height, int bitrate, int fps);

    /**
     * 设置音频参数
     *
     * @param sampleRateInHz
     * @param channel
     */
    public native void setAudioOptions(int sampleRateInHz, int channel);


    /**
     * 发送视频数据
     *
     * @param data
     */
    public native void fireVideo(byte[] data);

    /**
     * 发送音频数据
     *
     * @param data
     * @param len
     */
    public native void fireAudio(byte[] data, int len);

    public void setOnLiveStateChangeListener(OnLiveStateChangeListener onLiveStateChangeListener) {
        this.onLiveStateChangeListener = onLiveStateChangeListener;
    }

    public void removeListener() {
        if (onLiveStateChangeListener != null) {
            onLiveStateChangeListener = null;
        }
    }

    static {
        System.loadLibrary("jing_live");
    }

}
