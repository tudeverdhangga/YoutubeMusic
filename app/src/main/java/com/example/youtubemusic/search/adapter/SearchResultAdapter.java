package com.example.youtubemusic.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubemusic.databinding.ItemSearchResultBinding;
import com.example.youtubemusic.model.Items;
import com.squareup.picasso.Picasso;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{
    private Items[] searchResults;
    private LayoutInflater layoutInflater;
    private ItemClickListener listener;

    public SearchResultAdapter(Items[] searchResults, LayoutInflater layoutInflater) {
        this.searchResults = searchResults;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchResultAdapter.ViewHolder(ItemSearchResultBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvSongName.setText(searchResults[position].getSnippet().getTitle());
        holder.binding.tvArtistName.setText(searchResults[position].getSnippet().getChannelTitle());
        holder.binding.tvAlbumName.setText(searchResults[position].getSnippet().getPublishedAt());
        Picasso.get().load(searchResults[position].getSnippet().getThumbnails().getMedium().getUrl()).into(holder.binding.ivAlbum);
        holder.binding.cardSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(searchResults[position].getSnippet().getTitle());
                listener.onItemClick(searchResults[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResults != null? searchResults.length : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemSearchResultBinding binding;

        public ViewHolder(ItemSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            System.out.println(binding.tvSongName.getText());
        }
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(Items searchResult);
    }
}
