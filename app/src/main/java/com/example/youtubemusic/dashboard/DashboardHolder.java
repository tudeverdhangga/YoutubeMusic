package com.example.youtubemusic.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubemusic.R;

public class DashboardHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textView11, textView12;


    public DashboardHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView5);
        textView11 = itemView.findViewById(R.id.textView11);
        textView12 = itemView.findViewById(R.id.textView12);
    }
}
