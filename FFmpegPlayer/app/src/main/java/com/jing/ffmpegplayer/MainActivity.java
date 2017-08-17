package com.jing.ffmpegplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jing.ffmpegplayer.view.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private JingPlayer jingPlayer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.video_view);
        jingPlayer = new JingPlayer();

    }


    public void mPlay(View view) {
        /*String input = new File(Environment.getExternalStorageDirectory(), "input.mp4").getAbsolutePath();
        jingPlayer.render(input, videoView.getHolder().getSurface());*/

        String input = new File(Environment.getExternalStorageDirectory(), "input.mp3").getAbsolutePath();
        String output = new File(Environment.getExternalStorageDirectory(), "input.pcm").getAbsolutePath();

        jingPlayer.sound(input,output);
    }

}
