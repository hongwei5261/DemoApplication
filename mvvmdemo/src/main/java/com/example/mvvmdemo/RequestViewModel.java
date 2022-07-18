package com.example.mvvmdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class RequestViewModel extends AndroidViewModel {

    private MutableLiveData<String> resultData = new MutableLiveData<>();
    public RequestViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getResultData() {
        return resultData;
    }

    public void requestData() {
        Repository.getInstance().requestData(getResultData());
    }

}
