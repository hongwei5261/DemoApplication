package com.example.breakpointdemo;

import android.app.Application;
import android.content.Intent;

public class TestApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        this.startActivity(new Intent());
    }
}
