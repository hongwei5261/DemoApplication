package com.example.mvvmdemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 全局vm，可以同时给多个fragment使用
 */
public class ProjectViewModel extends ViewModel {
    private MutableLiveData<String> projectData = new MutableLiveData<>();

    private MutableLiveData<String> getProjectData() {
        return projectData;
    }
}
