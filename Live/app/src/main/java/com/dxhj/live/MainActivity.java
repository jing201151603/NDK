package com.dxhj.live;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.dxhj.live.push.LivePusher;

public class MainActivity extends Activity {

    static final String URL = "rtmp://112.74.96.116/live/jason";
    private LivePusher live;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO}, 10);

        setContentView(R.layout.activity_main);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
        //相机图像的预览
        live = new LivePusher(surfaceView.getHolder());
    }

    /**
     * 开始直播
     *
     * @param view
     */
    public void mStartLive(View view) {
        Button btn = (Button) view;
        if (btn.getText().equals("开始直播")) {
            live.startPush(URL);
            btn.setText("停止直播");
        } else {
            live.stopPush();
            btn.setText("开始直播");
        }
    }

    /**
     * 切换摄像头
     *
     * @param btn
     */
    public void mSwitchCamera(View btn) {
        live.switchCamera();
    }

}
