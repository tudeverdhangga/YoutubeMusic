package com.example.youtubemusic.login;

import com.google.firebase.auth.AuthCredential;

public class LoginContract {
    interface View {
        void loginSuccess(String message);
        void loginFailed(String message);
        void redirectToHome();

    }

    interface Presenter {
        void login(String email, String password);

        boolean hasUser();

    }
}
