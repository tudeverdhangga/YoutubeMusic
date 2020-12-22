package com.example.youtubemusic.register;

import com.example.youtubemusic.api_response.RegisterResponse;
import com.example.youtubemusic.callback.RequestCallback;
import com.example.youtubemusic.util.SharedPreferencesUtil;

public class RegisterInteractor implements RegisterContract.Interactor {

    private SharedPreferencesUtil sharedPreferencesUtil;

    public RegisterInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }


    @Override
    public void requestRegister(String username, String email, String password, String confirmPassword, RequestCallback<RegisterResponse> requestCallback) {

    }

    @Override
    public void saveToken(String token) {

    }
}
