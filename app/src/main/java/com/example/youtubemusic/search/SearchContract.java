package com.example.youtubemusic.search;


import com.example.youtubemusic.model.Items;

public interface SearchContract {
    interface View {
        void showSearchResultSuccess(Items[] listSearchResult);
        void showSearchResultFailed(String message);
        void redirectLogin();
    }

    interface Presenter {
        void requestSearch(String q);
        void logout();
    }

}
