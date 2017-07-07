package com.jing.appupdate;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jing.appupdate.utils.ApkUtils;
import com.jing.appupdate.utils.BsDiff;
import com.jing.appupdate.utils.BsPatch;
import com.jing.appupdate.utils.Constants;
import com.jing.appupdate.utils.DownloadUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String sdCard = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdCard = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar;


    }

    public void diff(View view) {
        String oldPath = sdCard + "app_old.apk";
        String newPath = sdCard + "app_new.apk";
        String patchPath = sdCard + "app.patch";
        BsDiff.diff(oldPath, newPath, patchPath);
    }

    public void patch(View view) {
//        new Task().execute();

        String oldFile = sdCard + "app_old.apk";
        String newFile = sdCard + "app_new2.apk";
        String patchFile = sdCard + "app.patch";
        BsPatch.patch(oldFile, newFile, patchFile);

    }


    class Task extends AsyncTask<Boolean, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Boolean... booleen) {

            try {
                //1.下载拆分宝
                File patchPath = DownloadUtils.download(Constants.URL_PATCH_DOWNLOAD);

                //2.合并成最新的安装包
                String oldFile = ApkUtils.getSourceApkPath(MainActivity.this, getPackageName());
                String newFile = Constants.NEW_APK_PATH;
                String patchFile = patchPath.getAbsolutePath();
                BsPatch.patch(oldFile, newFile, patchFile);
            } catch (Exception e) {
                e.getStackTrace();
                return false;
            }

            return true;
        }


        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);

            //3.执行安装
//            ApkUtils.installApk(getApplicationContext(), Constants.NEW_APK_PATH);

        }
    }

}
