package com.example.youtubemusic.play;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youtubemusic.API.SingletonRetrofitObject;
import com.example.youtubemusic.R;
import com.example.youtubemusic.model.Items;
import com.example.youtubemusic.model.VideoModel;
import com.example.youtubemusic.util.UtilProvider;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayActivity extends YouTubeBaseActivity implements PlayContract.View{
    private ImageButton play_btn;
    private ImageButton pause_btn;
    private PlayContract.Presenter presenter;
    private TextView title_tv;
    private TextView singer_tv;
    private ImageView cover_iv;
    private Items[] items;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        presenter = new PlayPresenter(this, UtilProvider.getSharedPreferencesUtil());

        title_tv = findViewById(R.id.title_tv);
        singer_tv = findViewById(R.id.singer_tv);
        cover_iv = findViewById(R.id.cover_img);
        youTubePlayerView = findViewById(R.id.player_view);
        play_btn = findViewById(R.id.play_btn);
        pause_btn = findViewById(R.id.pause_btn);

        String id = getIntent().getStringExtra("VIDEO_ID");
        doApiCall(id);
        setVideo(id);
    }

    private void setVideo(String id) {
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                setYoutubePlayer(youTubePlayer);
                if (!b){
                    youTubePlayer.loadVideo(id);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause_btn.setVisibility(View.VISIBLE);
                play_btn.setVisibility(View.INVISIBLE);
                youTubePlayerView.initialize(getString(R.string.youtube_api), mOnInitializedListener);
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause_btn.setVisibility(View.INVISIBLE);
                play_btn.setVisibility(View.VISIBLE);
                youTubePlayer.pause();
            }
        });
    }

    private void setYoutubePlayer(YouTubePlayer youTubePlayer) {
        this.youTubePlayer = youTubePlayer;
    }

    private void doApiCall(String id) {
        Call<VideoModel> videoModelCall = SingletonRetrofitObject.getmInstance().getApi().getVideoDetails(
                getString(R.string.youtube_api),
                "UCTFFPzHv1VZiVww1siqJi9Q",
                "snippet",
                "date",
                "20",
                "video"
        );

        videoModelCall.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                items = response.body().getItems();
                for (int i=0; i<items.length; i++) {
                    if (items[i].getId().getVideoId().equals(id)){
                        title_tv.setText(items[i].getSnippet().getTitle());
                        singer_tv.setText(items[i].getSnippet().getChannelTitle());
                        new DownloadImageTask(cover_iv).execute(items[i].getSnippet().getThumbnails().getHigh().getUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {

            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}