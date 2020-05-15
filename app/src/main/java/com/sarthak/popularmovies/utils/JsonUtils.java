package com.sarthak.popularmovies.utils;

import android.content.Context;

import com.sarthak.popularmovies.R;
import com.sarthak.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String KEY_NAME = "title";
    private static String KEY_IMAGE = "poster_path";
    private static String KEY_OVERVIEW = "overview";
    private static String KEY_RATING = "vote_average";
    private static String KEY_RELEASE = "release_date";
    private static String KEY_RESULT = "results";
    String base_url = "http://image.tmdb.org/t/p/w185";

    public static Movie parseMovieJson(String json) throws JSONException {

        JSONObject movieObject;
        JSONArray resultObject;
        JSONObject object;
        String name;
        String image;
        String overview;
        float rating;
        String release;
        Movie movie;
        ArrayList<String> Posters = new ArrayList<>();
        ArrayList<Integer> ids;

        movieObject = new JSONObject(json);
        resultObject = movieObject.getJSONArray(KEY_RESULT);

       for(int i = 0; i< resultObject.length(); i++){

           object = resultObject.getJSONObject(i);
           Posters.add(object.getString(KEY_IMAGE));

       }
        return new Movie();
    }
}
