package com.dxhj.live.push;

import java.io.IOException;


import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

import com.dxhj.live.jni.PushNative;
import com.dxhj.live.params.VideoParam;

public class VideoPusher extends Pusher {

    private SurfaceHolder surfaceHolder;
    private Camera mCamera;
    private VideoParam videoParams;
    private byte[] buffers;
    private boolean isPushing = false;
    private PushNative pushNative;

    public VideoPusher(SurfaceHolder surfaceHolder, VideoParam videoParams, PushNative pushNative) {
        this.surfaceHolder = surfaceHolder;
        this.videoParams = videoParams;
        this.pushNative = pushNative;
        surfaceHolder.addCallback(callback);
    }

    @Override
    public void startPush() {
        //设置视频的参数
        pushNative.setVideoOptions(videoParams.getWidth(), videoParams.getHeight(),
                videoParams.getBitratel(), videoParams.getFps());
        isPushing = true;
    }

    @Override
    public void stopPush() {
        isPushing = false;
    }

    private Callback callback = new Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            startPreview();

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            stopPreview();
        }
    };

    @Override
    public void release() {
        stopPreview();
    }


    /**
     * 切换摄像头
     */
    public void switchCamera() {
        if (videoParams.getCameraId() == CameraInfo.CAMERA_FACING_BACK) {
            videoParams.setCameraId(CameraInfo.CAMERA_FACING_FRONT);
        } else {
            videoParams.setCameraId(CameraInfo.CAMERA_FACING_BACK);
        }
        //重新预览
        stopPreview();
        startPreview();
    }

    /**
     * 开始预览
     */
    private void startPreview() {
        try {
            Log.i("jing", "开始camera startPreview");
            //SurfaceView初始化完成，开始相机预览
            mCamera = Camera.open(videoParams.getCameraId());
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPreviewFormat(ImageFormat.NV21);//YUV 预览图像的像素格式
            parameters.setPreviewSize(videoParams.getWidth(), videoParams.getHeight());
            mCamera.setParameters(parameters);
            //parameters.setPreviewFpsRange(videoParams.getFps()-1, videoParams.getFps());
            mCamera.setPreviewDisplay(surfaceHolder);
            //获取预览图像数据
            buffers = new byte[videoParams.getWidth() * videoParams.getHeight() * 4];
            mCamera.addCallbackBuffer(buffers);
            mCamera.setPreviewCallbackWithBuffer(previewCallback);

            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止预览
     */
    private void stopPreview() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }


    /**
     * 预览的回调
     */
    private PreviewCallback previewCallback = new PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            if (mCamera != null) {
                mCamera.addCallbackBuffer(buffers);
            }

            if (isPushing) {
                //回调函数中获取图像数据，然后给Native代码编码
                pushNative.fireVideo(data);
            }
        }
    };

}
