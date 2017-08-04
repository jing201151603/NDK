package com.dxhj.ffmpegwithposix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dxhj.ffmpegwithposix.view.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private JingPlayer player;
    private VideoView videoView;
    private Spinner sp_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.video_view);
        sp_video = (Spinner) findViewById(R.id.sp_video);
        player = new JingPlayer();
        //多种格式的视频列表
        String[] videoArray = getResources().getStringArray(R.array.video_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, videoArray);
        sp_video.setAdapter(adapter);
    }

    public void mPlay(View btn) {
        String input = "/mnt/sdcard/input.mp4";
        File file = new File(input);
        Toast.makeText(getApplicationContext(), file.exists() + "", Toast.LENGTH_SHORT).show();
        //Surface传入到Native函数中，用于绘制
        Surface surface = videoView.getHolder().getSurface();
        //player.render(input, surface);

        //String input = new File(Environment.getExternalStorageDirectory(),"hehuoren.flv").getAbsolutePath();
        //String output = new File(Environment.getExternalStorageDirectory(),"Justin Bieber - Boyfriend.pcm").getAbsolutePath();
        //player.sound(input, output);

        player.play(input, surface);
    }
}
