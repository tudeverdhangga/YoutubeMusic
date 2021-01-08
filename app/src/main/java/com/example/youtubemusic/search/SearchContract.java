package com.example.youtubemusic.search;


import java.util.List;

public interface SearchContract {
    interface View {
        void showSearchResultSuccess(List<SearchResult> listSearchResult);
        void showSearchResultFailed(String message);
        void redirectLogin();
    }

    interface Presenter {
        void requestSearch(String q);
        void logout();
    }

    interface Interactor {
//        void requestSearch(String q, RequestCallback<SearchResultResponse> requestCallback);
    }
}
