package com.example.breakpointdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                DownloadUtils.startDownload(getApplicationContext());
                break;
            case R.id.btn_pause:
                DownloadUtils.pauseDownload();
                break;
            case R.id.btn_cancel:
                DownloadUtils.cancelDownload();
                break;
            default:
                break;
        }
    }
}
