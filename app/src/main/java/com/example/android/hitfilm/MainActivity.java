package com.example.android.hitfilm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.hitfilm.AsyncTask.AsyncTaskCompleteListener;
import com.example.android.hitfilm.AsyncTask.FetchFilmDataTask;
import com.example.android.hitfilm.data.FilmInfo;
import com.example.android.hitfilm.enums.SortCriteria;
import com.example.android.hitfilm.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HitFilmAdapter.FilmAdapterOnClickHandler{

    private static final int COLUMNS_NUM = 4;
    private RecyclerView mRecyclerView;
    private HitFilmAdapter mHitFilmAdapter;
    public ProgressBar mLoadingProgressBar;
    private TextView mErrorTextView;
    private Spinner orderSpinner;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderSpinner = (Spinner) findViewById(R.id.sp_order);
        mLoadingProgressBar = (ProgressBar)findViewById(R.id.pb_loading_indicator);
        mErrorTextView = (TextView)findViewById(R.id.tv_error_message_display);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_hitfilm);

        /*Setting Spinner*/
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.order_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(spinnerAdapter);
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    loadFilmData(SortCriteria.POPULAR);
                }else if(position == 1){
                    loadFilmData(SortCriteria.RATED);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Setting RecyclerView*/
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, COLUMNS_NUM);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        mHitFilmAdapter = new HitFilmAdapter(this);
        mRecyclerView.setAdapter(mHitFilmAdapter);


        /*Fetch movie data*/
        loadFilmData(SortCriteria.POPULAR);

    }

    private void loadFilmData(Integer criteria){
        showMovieDataView();
        new FetchFilmDataTask(this, new FetchMyDataTaskCompleteListener()).execute(criteria);
//        new FetchHitFilmTask().execute(criteria);
    }

    private void showMovieDataView() {
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(FilmInfo filmInfo) {
        Class destinationClass = MovieDetailActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra("clicked_film", filmInfo);
        startActivity(intent);
    }

    public class FetchMyDataTaskCompleteListener implements AsyncTaskCompleteListener<List<FilmInfo>> {

        @Override
        public void onTaskComplete(List<FilmInfo> filmInfos) {
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
            if(filmInfos != null){
                mHitFilmAdapter.setFilmInfoList(filmInfos);
            }else{
                showErrorMessage();
            }


        }

        @Override
        public void onTaskPreStart() {
            mLoadingProgressBar.setVisibility(View.VISIBLE);
        }
    }

}
