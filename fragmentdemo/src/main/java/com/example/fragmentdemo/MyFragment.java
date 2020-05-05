package com.example.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.DLog;

public class MyFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DLog.d("onAttach-------->");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DLog.d("onCreate-------->");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DLog.d("onCreateView-------->");
        View v = inflater.inflate(R.layout.item_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DLog.d("onActivityCreated-------->");
    }

    @Override
    public void onResume() {
        super.onResume();
        DLog.d("onResume-------->");
    }

    @Override
    public void onPause() {
        super.onPause();
        DLog.d("onPause-------->");
    }

    @Override
    public void onStop() {
        super.onStop();
        DLog.d("onStop-------->");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DLog.d("onDestroyView-------->");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DLog.d("onDestroy-------->");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DLog.d("onDetach-------->");
    }
}
