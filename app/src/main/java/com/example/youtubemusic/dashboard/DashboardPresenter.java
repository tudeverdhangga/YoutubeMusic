package com.example.youtubemusic.dashboard;

import com.example.youtubemusic.search.SearchContract;
import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardPresenter implements DashboardContract.Presenter{
    private SharedPreferencesUtil sharedPreferencesUtil;
    private DashboardContract.View view;
    private FirebaseAuth mAuth;

    public DashboardPresenter(DashboardContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        mAuth = FirebaseAuth.getInstance();
    }

    public void logout() {
        mAuth.signOut();
        sharedPreferencesUtil.clear();
        view.redirectLogin();
    }
}
