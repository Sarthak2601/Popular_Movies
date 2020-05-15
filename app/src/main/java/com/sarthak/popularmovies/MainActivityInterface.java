package com.sarthak.popularmovies;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sarthak.popularmovies.adapter.MovieAdapter;
import com.sarthak.popularmovies.model.Movie;

public interface MainActivityInterface {
    String getSortBy();
    MovieAdapter getMovieAdapter();
    TextView getErrorMessage();
    ProgressBar getLoadingIndicator();
    RecyclerView getMovieGrid();
    void startDetailActivity(Movie movie);
}
