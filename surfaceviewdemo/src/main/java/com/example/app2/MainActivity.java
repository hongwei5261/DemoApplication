package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, CustomActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, MediaActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, CameraActivity.class));
                break;
        }
    }
}
