package com.example.com.demoapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;


public class MainActivity extends Activity {
    DownloadProgressBar mDownloadBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDownloadBar = (DownloadProgressBar) this.findViewById(R.id.downloadbar);
        func();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
            mDownloadBar.setCurProgress(progress);
        }
    };

    private void func() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100) {
                    mHandler.removeMessages(0);
                    Message message = new Message();
                    message.arg1 = progress;
                    message.what = 0;
                    mHandler.sendMessage(message);
                    progress++;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
