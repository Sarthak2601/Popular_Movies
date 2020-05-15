package com.sarthak.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sarthak.popularmovies.adapter.MovieAdapter;
import com.sarthak.popularmovies.model.Movie;
import com.sarthak.popularmovies.utils.JsonUtils;
import com.sarthak.popularmovies.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MainActivityInterface{

    private String sortBy;
    private MovieAdapter movieAdapter;
    private RecyclerView movieGrid;
    private TextView errorMessage;
    private ProgressBar loadingIndicator;
    private static final String SORT_BY_MOST_POPULAR = "popularity.desc";
    private static final String SORT_BY_HIGHEST_RATED = "vote_average.desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieGrid = findViewById(R.id.home_recyclerView);
        loadingIndicator = findViewById(R.id.home_progressBar);
        int spanCount = getResources().getInteger(R.integer.span_count);
        GridLayoutManager layoutManager = new GridLayoutManager(this,spanCount);
        movieGrid.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(this);
        movieGrid.setAdapter(movieAdapter);
        errorMessage = findViewById(R.id.error_message);
        sortBy = "popularity.desc";
        fetchMovies();

        /*new LoadData().execute(NetworkUtils.buildUrl());
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, movie);
        recyclerView.setAdapter(recyclerViewAdapter);*/
    }
    public String getSortBy(){ return sortBy; }

    public MovieAdapter getMovieAdapter() {
        return movieAdapter;
    }

    public TextView getErrorMessage(){ return errorMessage; }

    public ProgressBar getLoadingIndicator() {
        return loadingIndicator;
    }

    public RecyclerView getMovieGrid() {
        return movieGrid;
    }

    public void startDetailActivity(Movie movie) {

    } //Put Intent here for Detail Activity

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_most_popular :
                sortBy = SORT_BY_MOST_POPULAR;
                fetchMovies();
                return true;
            case R.id.action_highest_rated :
                sortBy = SORT_BY_HIGHEST_RATED;
                fetchMovies();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchMovies(){
        movieGrid.setVisibility(View.INVISIBLE);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info != null && info.isConnectedOrConnecting()){
            errorMessage.setVisibility(View.INVISIBLE);
            loadingIndicator.setVisibility(View.VISIBLE);
            new FetchMoviesTask().execute(this);
        }
        else {
            errorMessage.setVisibility(View.VISIBLE);
            loadingIndicator.setVisibility(View.INVISIBLE);
        }
    }

    /*public class LoadData extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String json = null;
            try {
                json = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null) {
                try {
                    movie = JsonUtils.parseMovieJson(s);
                    Toast.makeText(MainActivity.this, "Data received ", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Data received ", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Data error", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }*/
}
