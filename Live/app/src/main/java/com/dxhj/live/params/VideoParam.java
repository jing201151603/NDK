package com.dxhj.live.params;

/**
 * author: 陈永镜 .
 * date: 2017/8/8 .
 * email: jing20071201@qq.com
 * <p>
 * introduce:视频数据参数
 */
public class VideoParam {

    private int width;
    private int height;
    private int cameraId;
    //码率
    private int bitratel = 480000;//比特率480kbps
    //帧频
    private int fps = 25;//默认值25帧/s

    public VideoParam(int width, int height, int cameraId) {
        super();
        this.width = width;
        this.height = height;
        this.cameraId = cameraId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }


    public int getBitratel() {
        return bitratel;
    }

    public void setBitratel(int bitratel) {
        this.bitratel = bitratel;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }
}
