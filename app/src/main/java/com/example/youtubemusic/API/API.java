package com.example.youtubemusic.API;

import com.example.youtubemusic.model.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("search")
    Call<VideoModel> getVideoDetails(@Query("key") String key,
                                     @Query("channelId") String channelId,
                                     @Query("part") String part,
                                     @Query("order") String order,
                                     @Query("maxResults") String maxResults,
                                     @Query("type") String type);

    @GET("search")
    Call<VideoModel> getSearchMusicQuery(@Query("key") String key,
                                         @Query("part") String part,
                                         @Query("type") String type,
                                         @Query("order") String order,
                                         @Query("maxResults") String maxResults,
                                         @Query("q") String q,
                                         @Query("channelId") String channelId);
    @GET("search")
    Call<VideoModel> getVideoQuery(@Query("key") String key,
                                   @Query("part") String part,
                                   @Query("id") String id);

}
