package com.cogniwide.cogniwidetask;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import com.cogniwide.cogniwidetask.databinding.ActivityPopulerMoviesBinding;

import adapter.CustomAdapter;
import adapter.ItemViewModel;
import model.PopularMoviesModel;

public class PopularMoviesActivity extends AppCompatActivity {

    private ActivityPopulerMoviesBinding mPopularMoviesBinding;
    private ItemViewModel mItemViewModel;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mPopularMoviesBinding = DataBindingUtil.setContentView(this, R.layout.activity_populer_movies);
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mPopularMoviesBinding.recyclerMovies.setLayoutManager(mGridLayoutManager);
        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        CustomAdapter customAdapter = new CustomAdapter(this);
        mItemViewModel.itemPagedList.observe(this, new Observer<PagedList<PopularMoviesModel>>() {
            @Override
            public void onChanged(PagedList<PopularMoviesModel> popularMoviesModels) {
                customAdapter.submitList(popularMoviesModels);
                mPopularMoviesBinding.recyclerMovies.setAdapter(customAdapter);
            }
        });
    }
}
