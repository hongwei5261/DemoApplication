package com.example.mvvmdemo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class BaseActivity extends AppCompatActivity {

    ProjectViewModel projectViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
//        View view;
//        view.invalidate();
    }
}
