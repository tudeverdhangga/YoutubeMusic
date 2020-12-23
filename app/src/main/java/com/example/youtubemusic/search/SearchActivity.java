package com.example.youtubemusic.search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.youtubemusic.databinding.ActivitySearchBinding;
import com.example.youtubemusic.search.adapter.SearchResultAdapter;
import com.example.youtubemusic.util.UtilProvider;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, View.OnClickListener {
    private SearchContract.Presenter presenter;
    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SearchPresenter(this, new SearchInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    private void initView() {
        binding.rvResultSearch.setLayoutManager(new LinearLayoutManager(this));
        binding.btnSearch.setOnClickListener(this);
        binding.etSearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    binding.btnSearch.performClick();
                }
                return false;
            }
        });
    }

    @Override
    public void showSearchResultSuccess(List<SearchResult> listSearchResult) {
        binding.rvResultSearch.setAdapter(new SearchResultAdapter(listSearchResult, getLayoutInflater()));
        ((SearchResultAdapter) binding.rvResultSearch.getAdapter()).setOnItemClickListener(new SearchResultAdapter.ItemClickListener() {
            @Override
            public void onItemClick(SearchResult searchResult) {
                //
            }
        });
    }

    @Override
    public void showSearchResultFailed(String message) {
        binding.tvErrorMessage.setText(message);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnSearch.getId())
            onSearchClicked();
    }

    private void onSearchClicked() {
        if(binding.etSearchBox.getText().toString().equals(""))
            return;

        String q = binding.etSearchBox.getText().toString();
        presenter.requestSearch(q);
    }
}
