package com.example.mvvmdemo;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;

public class App extends Application {
    ViewModelProvider viewModelProvider;

    @Override
    public void onCreate() {
        super.onCreate();

    }


    public ViewModelProvider getViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(new ViewModelStore(),
                    ViewModelProvider.AndroidViewModelFactory.getInstance(this));
        }
        return viewModelProvider;
    }
}
