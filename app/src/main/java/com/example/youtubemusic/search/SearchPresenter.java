package com.example.youtubemusic.search;

public class SearchPresenter implements SearchContract.Presenter{

    SearchContract.View view;
    SearchContract.Interactor interactor;

    public SearchPresenter(SearchContract.View view, SearchContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestSearch(String q) {

    }
}
