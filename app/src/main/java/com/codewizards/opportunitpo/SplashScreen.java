package com.codewizards.opportunitpo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        logo=findViewById(R.id.splash_logo);

        Animation move= AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        logo.startAnimation(move);

        //intro audio
//        MediaPlayer mp=new MediaPlayer();
//        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        String path="android.resource://"+getPackageName()+"/raw/carengine";
//        Uri audioUri=Uri.parse(path);
//        try {
//            mp.setDataSource(this,audioUri);
//            mp.prepare();
//            mp.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        Intent iHome=new Intent(SplashScreen.this,MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(iHome);
                //intro audio stop
//                mp.stop();
                finish();
            }
        },3000);
    }
}