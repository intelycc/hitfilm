package com.example.android.hitfilm.AsyncTask;

/**
 * Created by intelycc on 17/5/6.
 */

public interface AsyncTaskCompleteListener<T> {
    void onTaskComplete(T result);

    void onTaskPreStart();
}
