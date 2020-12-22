package com.example.youtubemusic.register;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubemusic.databinding.ActivityRegisterBinding;
import com.example.youtubemusic.util.UtilProvider;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    private RegisterContract.Presenter presenter;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RegisterPresenter(this, new RegisterInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView() {
    }

    @Override
    public void registerSuccess(String message) {

    }

    @Override
    public void registerFailed(String message) {

    }
}
