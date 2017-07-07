package com.jing.fmodchangevoice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.fmod.FMOD;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FMOD.init(this);
        setContentView(R.layout.activity_main);

    }

    public void mFix(View view) {
        String path = "/mnt/sdcard/test.mp3";
        File file=new File(path);
        Toast.makeText(getApplicationContext(),file.exists()+"",Toast.LENGTH_SHORT).show();

        switch (view.getId()){
            case R.id.btn_record://原生
                EffectUtils.fix(path, EffectUtils.MODE_NORMAL);
                break;
            case R.id.btn_luoli:
                EffectUtils.fix(path,EffectUtils.MODE_LUOLI);
                break;
            case R.id.btn_jingsong:

                EffectUtils.fix(path,EffectUtils.MODE_JINGSONG);
                break;
            case R.id.btn_gaoguai:

                EffectUtils.fix(path,EffectUtils.MODE_GAOGUAI);
                break;
            case R.id.btn_kongling:

                EffectUtils.fix(path,EffectUtils.MODE_KONGLING);
                break;
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();

    }
}
