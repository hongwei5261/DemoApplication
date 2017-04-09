package com.customscrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onScrollView1(View view) {
        startActivity(new Intent(this, ScrollViewActivity1.class));
    }

    public void onScrollView2(View view) {
        startActivity(new Intent(this, ScrollViewActivity2.class));
    }

    public void onScrollView3(View view) {
        startActivity(new Intent(this, ScrollViewActivity3.class));
    }
}
