package com.example.youtubemusic;

import android.app.Application;

import com.example.youtubemusic.util.UtilProvider;

public class Starter extends Application {
    public void onCreate() {
        super.onCreate();

        UtilProvider.initialize(getApplicationContext());
    }
}
