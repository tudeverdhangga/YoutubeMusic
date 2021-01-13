package com.example.youtubemusic.search;

import android.util.Log;

import com.example.youtubemusic.API.SingletonRetrofitObject;
import com.example.youtubemusic.constant.ApiConstant;
import com.example.youtubemusic.model.VideoModel;
import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.Presenter{
    private SharedPreferencesUtil sharedPreferencesUtil;
    private SearchContract.View view;
    private FirebaseAuth mAuth;

    public SearchPresenter(SearchContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        mAuth = FirebaseAuth.getInstance();
    }

    public void logout() {
        mAuth.signOut();
        sharedPreferencesUtil.clear();
        view.redirectLogin();
    }

    @Override
    public void requestSearch(String q) {
        Call<VideoModel> searchResultCall = SingletonRetrofitObject.getmInstance().getApi()
                .getSearchMusicQuery(
                        ApiConstant.YOUTUBE_URL,
                        "snippet",
                        "video",
                        "rating",
                        "10",
                        q,
                        "UCTFFPzHv1VZiVww1siqJi9Q"
                );
        searchResultCall.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                view.showSearchResultSuccess(response.body().getItems());
                Log.d("RESPONSE", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                view.showSearchResultFailed(t.getMessage());
            }
        });
    }
}
