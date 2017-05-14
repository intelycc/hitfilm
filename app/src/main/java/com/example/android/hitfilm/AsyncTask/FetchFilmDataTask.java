package com.example.android.hitfilm.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.hitfilm.MainActivity;
import com.example.android.hitfilm.data.FilmInfo;
import com.example.android.hitfilm.enums.SortCriteria;
import com.example.android.hitfilm.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by intelycc on 17/5/6.
 */

public class FetchFilmDataTask extends AsyncTask<Integer, Void, List<FilmInfo>> {
    private Context context;
    private AsyncTaskCompleteListener listener;

    public FetchFilmDataTask(Context ctx, AsyncTaskCompleteListener listener){
        this.context = ctx;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskPreStart();
    }

    @Override
    protected List<FilmInfo> doInBackground(Integer... params) {
        List<FilmInfo> films = null;
        if(params.length == 0){
            return null;
        }

        URL url = NetworkUtils.buildUrl(params[0]);
        try {
            String filmStringData = NetworkUtils.getResponseFromHttpUrl(url);
            films = NetworkUtils.transformJsonData(filmStringData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return films;
    }

    @Override
    protected void onPostExecute(List<FilmInfo> filmInfos) {
        listener.onTaskComplete(filmInfos);

    }
}
