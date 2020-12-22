package com.example.youtubemusic.register;

import com.example.youtubemusic.api_response.RegisterResponse;
import com.example.youtubemusic.callback.RequestCallback;

public interface RegisterContract {
    interface View {
        void registerSuccess(String message);
        void registerFailed(String message);
    }

    interface Presenter {
        void register(String name, String phone, String email, String password, String confirmPassword);
    }

    interface Interactor {
        void requestRegister(String username, String email, String password, String confirmPassword, RequestCallback<RegisterResponse> requestCallback);
        void saveToken(String token);
    }
}
