package com.sarthak.popularmovies.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String BASE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";
    final static String API_KEY = "2237a815d9067d8313a0f4f5a87f29af";
    final static String PARAM_SORT = "sort";
    final static String sortBy = null;

    public static URL buildUrl(){
        Uri buildUri = Uri.parse(BASE_URL+API_KEY).buildUpon()
                //.appendQueryParameter(PARAM_SORT,sortBy)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
