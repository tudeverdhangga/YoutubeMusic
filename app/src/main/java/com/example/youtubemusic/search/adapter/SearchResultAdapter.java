package com.example.youtubemusic.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubemusic.databinding.ItemSearchResultBinding;
import com.example.youtubemusic.search.SearchResult;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{
    private List<SearchResult> searchResults;
    private LayoutInflater layoutInflater;
    private ItemClickListener listener;

    public SearchResultAdapter(List<SearchResult> searchResults, LayoutInflater layoutInflater) {
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
//        holder.binding.setSearchResult(searchResults.get(position));
        holder.binding.cardSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(searchResults.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemSearchResultBinding binding;

        public ViewHolder(ItemSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(SearchResult searchResult);
    }
}
