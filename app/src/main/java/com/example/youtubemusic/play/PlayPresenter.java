package com.example.youtubemusic.play;

import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.google.firebase.auth.FirebaseAuth;

public class PlayPresenter implements PlayContract.Presenter{
    private PlayContract.View view;
    private SharedPreferencesUtil sharedPreferencesUtil;

    public PlayPresenter(PlayContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

}
