package com.example.youtubemusic.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubemusic.R;
import com.example.youtubemusic.dashboard.DashboardHolder;
import com.example.youtubemusic.model.Items;
import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<DashboardHolder> {
    public MyAdapter(Context context, Items[] items) {
        this.context = context;
        this.items = items;
    }

    Context context;
    Items[] items;

    @NonNull
    @Override
    public DashboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_card, parent, false);

        return new DashboardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardHolder holder, int position) {
        holder.textView11.setText(items[position].getSnippet().getTitle());
        holder.textView12.setText(items[position].getSnippet().getChannelTitle());
        Picasso.get().load(items[position].getSnippet().getThumbnails().getMedium().getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return items.length;
    }
}
