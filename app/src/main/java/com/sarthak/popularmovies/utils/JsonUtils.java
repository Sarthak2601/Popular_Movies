package com.sarthak.popularmovies.utils;

import com.sarthak.popularmovies.model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class JsonUtils {

    private static String KEY_NAME = "title";
    private static String KEY_IMAGE = "poster_path";
    private static String KEY_OVERVIEW = "overview";
    private static String KEY_RATING = "vote_average";
    private static String KEY_RELEASE = "release_date";
    private static String KEY_RESULT = "results";

    public static Movie parseMovieJson(String json) throws JSONException {

        JSONObject movieObject;
        JSONObject resultObject;
        String name;
        String image;
        String overview;
        float rating;
        String release;
        Movie movie;

        movieObject = new JSONObject(json);
        resultObject = movieObject.getJSONObject(KEY_RESULT);
        name = resultObject.getString(KEY_NAME);
        image = resultObject.getString(KEY_IMAGE);
        overview = resultObject.getString(KEY_OVERVIEW);
        rating = BigDecimal.valueOf(resultObject.getDouble(KEY_RATING)).floatValue();
        release = resultObject.getString(KEY_RELEASE);
        movie = new Movie(name,image,overview,rating,release);

        return movie;
    }
}
