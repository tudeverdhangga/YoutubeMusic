package com.example.youtubemusic.search;

import com.example.youtubemusic.api_response.SearchResultResponse;
import com.example.youtubemusic.callback.RequestCallback;
import com.example.youtubemusic.util.SharedPreferencesUtil;

public class SearchInteractor implements SearchContract.Interactor{
    SharedPreferencesUtil sharedPreferencesUtil;

    public SearchInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestSearch(String q, RequestCallback<SearchResultResponse> requestCallback) {

    }
}
