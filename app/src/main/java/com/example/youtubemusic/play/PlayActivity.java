package com.example.youtubemusic.play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.youtubemusic.R;
import com.example.youtubemusic.login.LoginActivity;
import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.example.youtubemusic.util.UtilProvider;
import com.google.firebase.auth.FirebaseAuth;

public class PlayActivity extends AppCompatActivity implements PlayContract.View{
    private PlayContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        presenter = new PlayPresenter(this, UtilProvider.getSharedPreferencesUtil());
    }


}