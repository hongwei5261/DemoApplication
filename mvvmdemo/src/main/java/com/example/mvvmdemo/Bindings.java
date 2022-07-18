package com.example.mvvmdemo;

import android.app.Application;
import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


public class Bindings {


    @BindingAdapter(value = {"textContent"})
    public static void setText(TextView textView, String textContent) {
        textView.setText(textContent);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
    }
}
