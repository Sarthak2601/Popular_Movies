package com.sarthak.popularmovies;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sarthak.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FetchMoviesTask extends AsyncTask<MainActivityInterface, Void, ArrayList<Movie>> {

    private final static String URL = "https://api.themoviedb.org/3/discover/movie?api_key=";
    private final static String API_KEY = "2237a815d9067d8313a0f4f5a87f29af";
    private final static String BASE_URL = URL + API_KEY +"&language=en-US&include_adult=false&include_video=false&page=1&sort_by=";
    private MainActivityInterface mainActivityInterface;

    @Override
    protected ArrayList<Movie> doInBackground(MainActivityInterface... mainActivityInterfaces) {
        mainActivityInterface = mainActivityInterfaces[0];
        ArrayList<Movie> movies = new ArrayList<>();
        URL url = null ;
        try {
            url = new URL("https://api.themoviedb.org/3/discover/movie?api_key=2237a815d9067d8313a0f4f5a87f29af&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = connection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()){
                    try {
                        JSONObject json = new JSONObject(scanner.next());
                        JSONArray results = json.getJSONArray("results");
                        for(int i = 0; i< results.length(); i++){

                            JSONObject result = results.getJSONObject(i);
                            String title = result.getString("title");
                            String releaseDate = result.getString("release_date");
                            String posterPath = result.getString("poster_path");
                            String voteAverage = result.getString("vote_average");
                            String overview = result.getString("overview");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        movies.clear();
                    }
                }


            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                connection.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        TextView errorMessage = mainActivityInterface.getErrorMessage();
        ProgressBar loadingIndicator = mainActivityInterface.getLoadingIndicator();
        RecyclerView movieGrid = mainActivityInterface.getMovieGrid();
        loadingIndicator.setVisibility(View.INVISIBLE);
        if (movies.isEmpty()){
            errorMessage.setVisibility(View.VISIBLE);
            movieGrid.setVisibility(View.INVISIBLE);
        }
        else {
            errorMessage.setVisibility(View.INVISIBLE);
            mainActivityInterface.getMovieAdapter().setMovies(movies);
            movieGrid.setVisibility(View.VISIBLE);
        }
    }
}
