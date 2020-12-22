package com.example.youtubemusic.callback;

public interface RequestCallback<T> {
    void requestSuccess(T data);
    void requestFailed(String errorMessage);
}
