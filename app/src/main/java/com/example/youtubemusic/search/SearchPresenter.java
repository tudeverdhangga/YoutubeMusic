package com.example.youtubemusic.search;

import com.example.youtubemusic.API.SingletonRetrofitObject;
import com.example.youtubemusic.R;
import com.example.youtubemusic.model.VideoModel;
import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.provider.Settings.System.getString;

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

    }
}
