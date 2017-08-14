package com.dxhj.live.push;


import android.hardware.Camera.CameraInfo;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

import com.dxhj.live.jni.PushNative;
import com.dxhj.live.listener.OnLiveStateChangeListener;
import com.dxhj.live.params.AudioParam;
import com.dxhj.live.params.VideoParam;

public class LivePusher {

    private SurfaceHolder surfaceHolder;
    private VideoPusher videoPusher;
    private AudioPusher audioPusher;
    private PushNative pushNative;

    public LivePusher(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        surfaceHolder.addCallback(callback);
        prepare();
    }

    /**
     * 预览准备
     */
    private void prepare() {
        pushNative = new PushNative();

        //实例化视频推流器
        VideoParam videoParam = new VideoParam(480, 320, CameraInfo.CAMERA_FACING_BACK);
        videoPusher = new VideoPusher(surfaceHolder, videoParam, pushNative);

        //实例化音频推流器
        AudioParam audioParam = new AudioParam();
        audioPusher = new AudioPusher(audioParam, pushNative);
    }

    /**
     * 切换摄像头
     */
    public void switchCamera() {
        videoPusher.switchCamera();
    }

    /**
     * 开始推流
     */
    public void startPush(String url, OnLiveStateChangeListener onLiveStateChangeListener) {
        videoPusher.startPush();
        audioPusher.startPush();
        pushNative.startPush(url);
        pushNative.setOnLiveStateChangeListener(onLiveStateChangeListener);
    }


    /**
     * 停止推流
     */
    public void stopPush() {
        videoPusher.stopPush();
        audioPusher.stopPush();
        pushNative.stopPush();
        pushNative.removeListener();
    }

    /**
     * 释放资源
     */
    private void release() {
        videoPusher.release();
        audioPusher.release();
        pushNative.release();
    }

    private Callback callback = new Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            stopPush();
            release();
        }
    };

}
