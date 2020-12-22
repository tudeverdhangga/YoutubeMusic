package com.example.youtubemusic.search;

import com.example.youtubemusic.api_response.RegisterResponse;
import com.example.youtubemusic.api_response.SearchResultResponse;
import com.example.youtubemusic.callback.RequestCallback;

import java.util.List;

public interface SearchContract {
    interface View {
        void showSearchResultSuccess(List<SearchResult> listSearchResult);
        void showSearchResultFailed(String message);
    }

    interface Presenter {
        void requestSearch(String q);
    }

    interface Interactor {
        void requestSearch(String q, RequestCallback<SearchResultResponse> requestCallback);
    }
}
