package com.example.youtubemusic.dashboard;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubemusic.R;
import com.example.youtubemusic.dashboard.adapter.MyAdapter;
import com.example.youtubemusic.model.Items;

public class DashboardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imageView;
    public TextView textView11, textView12;
    private MyAdapter.OnClickListener mOnClickListener;
    private Items[] items;

    public DashboardHolder(@NonNull View itemView, MyAdapter.OnClickListener mOnClickListener, Items[] items) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView5);
        textView11 = itemView.findViewById(R.id.textView11);
        textView12 = itemView.findViewById(R.id.textView12);
        this.mOnClickListener = mOnClickListener;
        this.items = items;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        mOnClickListener.onItemClick(items[position].getId().getVideoId());
    }
}
