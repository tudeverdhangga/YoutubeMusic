package com.example.youtubemusic.dashboard;

import com.example.youtubemusic.model.Items;

public interface DashboardContract {
    interface View {
        void redirectLogin();
    }

    interface Presenter {
        void logout();
    }
}
