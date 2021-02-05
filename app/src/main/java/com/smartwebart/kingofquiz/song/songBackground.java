package com.smartwebart.kingofquiz.song;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.smartwebart.kingofquiz.R;

public class songBackground extends Activity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.quiz);

        mediaPlayer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }
}
