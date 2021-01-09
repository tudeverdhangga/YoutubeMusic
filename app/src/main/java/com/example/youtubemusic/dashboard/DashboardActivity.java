package com.example.youtubemusic.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.youtubemusic.API.SingletonRetrofitObject;
import com.example.youtubemusic.R;
import com.example.youtubemusic.dashboard.adapter.MyAdapter;
import com.example.youtubemusic.databinding.ActivitySearchBinding;
import com.example.youtubemusic.login.LoginActivity;
import com.example.youtubemusic.model.Items;
import com.example.youtubemusic.model.VideoModel;
import com.example.youtubemusic.search.SearchContract;
import com.example.youtubemusic.util.UtilProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {
    private RecyclerView recyclerView;
    private ImageView imageView;
    private DashboardContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new DashboardPresenter(this, UtilProvider.getSharedPreferencesUtil());

        recyclerView = findViewById(R.id.recycler1);
        imageView = findViewById(R.id.imageView7);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        doApiCall();
    }

    private void doApiCall(){
        Call<VideoModel> videoModelCall = SingletonRetrofitObject.getmInstance().getApi().getVideoDetails(
                getString(R.string.youtube_api),
                "UCTFFPzHv1VZiVww1siqJi9Q",
                "snippet",
                "date",
                "100",
                "video"
        );

        videoModelCall.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                setRecyclerView(response.body().getItems());
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {

            }
        });
    }

    private void setRecyclerView(Items[] items) {
        MyAdapter myAdapter = new MyAdapter(this, items);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void redirectLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}