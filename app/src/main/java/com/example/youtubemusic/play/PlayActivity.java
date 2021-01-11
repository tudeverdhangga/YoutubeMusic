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
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayActivity extends YouTubeBaseActivity implements PlayContract.View{
    private ImageButton play_btn;
    private ImageButton pause_btn;
    private ImageButton back_btn;
    private ImageButton next_btn;
    private PlayContract.Presenter presenter;
    private TextView title_tv;
    private TextView singer_tv;
    private ImageView cover_iv;
    private Items[] items;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
    private String id;
    private List<String> idPlaylist;
    private int index = 0;


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
        back_btn = findViewById(R.id.back_btn);
        next_btn = findViewById(R.id.next_btn);
        idPlaylist = new ArrayList<>();

        id = getIntent().getStringExtra("VIDEO_ID");
        doApiCall(id);
        setVideo();
        Log.d("ID_LAGU", "onCreate: " + id);
    }

    @Override
    protected void onStart() {
        super.onStart();

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause_btn.setVisibility(View.VISIBLE);
                play_btn.setVisibility(View.INVISIBLE);
                youTubePlayer.play();
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (youTubePlayer.isPlaying()) {
                    youTubePlayer.pause();
                    pause_btn.setVisibility(View.INVISIBLE);
                    play_btn.setVisibility(View.VISIBLE);
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!youTubePlayer.hasPrevious())
                    Toast.makeText(PlayActivity.this, "End of playlist", Toast.LENGTH_SHORT).show();
                else {
                    index--;
                    setInformation();
                    youTubePlayer.previous();
                }
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!youTubePlayer.hasNext())
                    Toast.makeText(PlayActivity.this, "End of playlist", Toast.LENGTH_SHORT).show();
                else {
                    index++;
                    setInformation();
                    youTubePlayer.next();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        idPlaylist.clear();
        index = 0;
    }

    private void setInformation() {
        title_tv.setText(items[index].getSnippet().getTitle());
        singer_tv.setText(items[index].getSnippet().getChannelTitle());
        new DownloadImageTask(cover_iv).execute(items[index].getSnippet().getThumbnails().getHigh().getUrl());
    }

    private void setVideo() {
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                setYoutubePlayer(youTubePlayer);
                if (!b){
                    youTubePlayer.cueVideos(idPlaylist, 0, 0);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                Toast.makeText(PlayActivity.this, "Oh no! " + error.toString(), Toast.LENGTH_LONG).show();
            }
        };
        youTubePlayerView.initialize(getString(R.string.youtube_api), mOnInitializedListener);
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
                    idPlaylist.add(items[i].getId().getVideoId());
                    if (items[i].getId().getVideoId().equals(id)){
                        title_tv.setText(items[i].getSnippet().getTitle());
                        singer_tv.setText(items[i].getSnippet().getChannelTitle());
                        new DownloadImageTask(cover_iv).execute(items[i].getSnippet().getThumbnails().getHigh().getUrl());
                    }
                }
                setPlaylist();
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {

            }
        });
    }

    private void setPlaylist() {
        int indexId = 0;
        String idVideo = "";
        String firstId = "";
        String temp = "";

        for (int i=0; i<items.length; i++) {
            if (items[i].getId().getVideoId().equals(id)){
                idVideo = id;
                indexId = i;
            }

            if (i == 0)
                firstId = items[i].getId().getVideoId();

            Log.d("LAGU", "Id Playlist: " + idPlaylist.get(i));
        }

        if (!idVideo.equals(firstId)) {
            temp = idVideo;
            idPlaylist.set(indexId, firstId);
            idPlaylist.set(0, temp);
        }
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