package com.example.app2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MediaActivity extends AppCompatActivity {
    SurfaceViewMedia surfaceViewMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surfaceview_media);
        surfaceViewMedia = findViewById(R.id.surfaceview);
        surfaceViewMedia.setSourcePath("device-2022-07-06-115814.mp4");

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surfaceViewMedia.startOrPause();
            }
        });
    }
}
