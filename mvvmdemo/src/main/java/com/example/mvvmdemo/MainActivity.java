package com.example.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    // 负责数据绑定显示
    ShowViewModel showViewModel;
    // 负责请求数据
    RequestViewModel requestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        showViewModel = ViewModelProviders.of(this).get(ShowViewModel.class);
        requestViewModel =  ViewModelProviders.of(this).get(RequestViewModel.class);
        activityMainBinding.setVm(showViewModel);
        activityMainBinding.setClick(new ProxyClick());
        activityMainBinding.setLifecycleOwner(this);

        // 监听数据改变，通过观察者模式，不需要单独的回调，从Repository到显示vm，解决了mvp接口地狱的问题
        requestViewModel.getResultData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String resultData) {
                showViewModel.showData.postValue("show " + resultData);
            }
        });
    }


    public class ProxyClick {

        public void onUpdateClick() {
            requestViewModel.requestData();
        }
    }

}
