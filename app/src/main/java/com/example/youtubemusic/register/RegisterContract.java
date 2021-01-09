package com.example.youtubemusic.register;

public interface RegisterContract {
    interface View {
        void registerSuccess(String message);
        void registerFailed(String message);
    }

    interface Presenter {
        void register(String username, String email, String password, String confirmPassword);
        boolean hasUser();
    }
}
