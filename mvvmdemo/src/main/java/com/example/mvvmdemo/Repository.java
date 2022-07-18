package com.example.mvvmdemo;

import androidx.lifecycle.MutableLiveData;

public class Repository {

    private static Repository sInstance;

    private Repository() {

    }

    public static Repository getInstance() {
        if (sInstance == null) {
            synchronized (Repository.class) {
                if (sInstance == null) {
                    sInstance = new Repository();
                }
            }
        }

        return sInstance;
    }

    static int i = 0;

    public void requestData(MutableLiveData<String> resultData) {
        // 1.加载本地

        // 2。加载网络

        resultData.postValue("bbbb" + (i++));
    }
}
