package com.example.mvvmdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ShowViewModel extends AndroidViewModel {
    public MutableLiveData<String> showData = new MutableLiveData<>();

    public ShowViewModel(@NonNull Application application) {
        super(application);
    }

}
