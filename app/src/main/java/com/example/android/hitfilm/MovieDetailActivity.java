package com.example.android.hitfilm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.hitfilm.config.TheMovieDbConfig;
import com.example.android.hitfilm.data.FilmInfo;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private FilmInfo filmInfo;
    private TextView movieTitleTextView;
    private ImageView movieImageView;
    private TextView movieReleaseDateTextView;
    private TextView movieRateTextView;
    private TextView movieIntroTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieTitleTextView = (TextView)findViewById(R.id.tv_movie_title);
        movieImageView = (ImageView)findViewById(R.id.iv_movie_image);
        movieReleaseDateTextView = (TextView)findViewById(R.id.tv_release_date);
        movieRateTextView = (TextView)findViewById(R.id.tv_rate);
        movieIntroTextView = (TextView)findViewById(R.id.tv_intro);


        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("clicked_film")) {
                filmInfo = (FilmInfo)intentThatStartedThisActivity.getSerializableExtra("clicked_film");

                movieTitleTextView.setText(filmInfo.getOriginalTitle());

                Uri imageUri = Uri.parse(TheMovieDbConfig.IMAGE_BASE_URL+filmInfo.getPosterPath());
                Picasso.with(this).load(imageUri.toString()).into(movieImageView);

                movieReleaseDateTextView.setText(filmInfo.getReleaseDate());

                movieRateTextView.setText(String.valueOf(filmInfo.getVoteAverage())+ "/10");

                movieIntroTextView.setText(filmInfo.getOverView());
            }
        }
    }
}
