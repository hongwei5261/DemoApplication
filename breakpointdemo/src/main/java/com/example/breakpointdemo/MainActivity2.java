package com.example.breakpointdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common.DLog;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DLog.d("MainActivity  onCreate");

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        new Thread(futureTask).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DLog.d("MainActivity2  onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        DLog.d("MainActivity2  onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        DLog.d("MainActivity2  onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        DLog.d("MainActivity2  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DLog.d("MainActivity2  onDestroy");
    }
}
