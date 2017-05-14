/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.hitfilm.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.hitfilm.config.TheMovieDbConfig;
import com.example.android.hitfilm.data.FilmInfo;
import com.example.android.hitfilm.enums.SortCriteria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the themoviedb servers.
 */
public final class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String TOP_RATED_PATH = "/movie/top_rated";
    private static final String POPULAR_PATH = "/movie/popular";

    private static final String API_KEY = "api_key";
    private static final String PAGE = "page";


    /**
     * Builds the URL used to talk to the themoviedb server.
     *
     * @param sortBy The sort condition.
     * @return The URL to use to query the themoviedb server.
     */
    public static URL buildUrl(Integer sortBy) {
        String apiAddress = TheMovieDbConfig.FILM_BASE_URL;

        if(sortBy.equals(SortCriteria.POPULAR)){
            apiAddress += POPULAR_PATH;
        }else if (sortBy.equals(SortCriteria.RATED)){
            apiAddress += TOP_RATED_PATH;
        }else{
            Log.w(TAG, "sortBy error!");
            return null;
        }

        Uri builtUri = Uri.parse(apiAddress).buildUpon()
                .appendQueryParameter(API_KEY, TheMovieDbConfig.API_KEY)
                .appendQueryParameter(PAGE, "1")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(10000);
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

    /**
     * This method transform string data to object.
     *
     * @param jsonString The source json string.
     * @return List of filminfo.
     */
    public static List<FilmInfo> transformJsonData(String jsonString){
        List<FilmInfo> films = new ArrayList<>();
        FilmInfo film;
        JSONObject jsonObject;

        if(jsonString == null){
            Log.w(TAG, "source jsonString is null");
            return null;
        }
        try {
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject filmObject = jsonArray.getJSONObject(i);
                film = new FilmInfo();
                film.setPosterPath(filmObject.getString("poster_path"));
                film.setAdult(filmObject.getBoolean("adult"));
                film.setOverView(filmObject.getString("overview"));
                film.setReleaseDate(filmObject.getString("release_date"));
                film.setVoteAverage(filmObject.getDouble("vote_average"));
                film.setOriginalTitle(filmObject.getString("original_title"));
                films.add(film);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return films;

    }
}