package com.example.youtubemusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.youtubemusic.API.SingletonRetrofitObject;
import com.example.youtubemusic.model.Items;
import com.example.youtubemusic.model.VideoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler1);

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
                "50",
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
}