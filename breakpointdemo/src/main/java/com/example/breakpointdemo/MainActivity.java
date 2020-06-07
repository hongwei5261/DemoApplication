package com.example.breakpointdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.common.DLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DLog.d("MainActivity  onCreate");
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
            case R.id.btn_cyc:
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                break;
            default:
                break;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        DLog.d("MainActivity  onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        DLog.d("MainActivity  onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        DLog.d("MainActivity  onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        DLog.d("MainActivity  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DLog.d("MainActivity  onDestroy");
    }
}
