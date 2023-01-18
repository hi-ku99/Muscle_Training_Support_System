package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class movie_play_scene extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_play_scene);

        // 元の画面で指定した動画を取得
        intent = getIntent();
        int mp4 = intent.getIntExtra("mp4",R.raw.dumbbell_curl);

        // ID取得
        VideoView v = findViewById(R.id.videoView1);

        // 動画の指定
        v.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + mp4));

        // 再生スタート
        v.start();

        // コントローラNO（動画をタップするとメニュー表示）
        v.setMediaController(new MediaController(this));

    }

    @Override
    public void onResume() {
        super.onResume();

        ImageView movie_end = findViewById(R.id.movie_end);
        movie_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Class pre_class = getCallingActivity().getClass();
                Intent intent = new Intent(getApplication(), pre_class);
                startActivity(intent);*/
                finish();
            }
        });
    }
}
