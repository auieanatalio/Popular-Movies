package com.auie.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import model.Movie;

public class MovieDetails extends AppCompatActivity {

    TextView movieTitle;
    TextView movieOverview;
    ImageView posterPath;
    ArrayList<Movie> movieList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieTitle = findViewById(R.id.movie_title);
        movieOverview = findViewById(R.id.movie_overview);
        posterPath = findViewById(R.id.poster_path);

        String title = getIntent().getExtras().getString("title");
        String overview = getIntent().getExtras().getString("overview");
        String poster_path = getIntent().getExtras().getString("poster_path");

        movieTitle.setText(getText(R.string.title) + title);
        movieOverview.setText(getText(R.string.overview) + overview);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/" + poster_path)
                .into(posterPath);

    }
}
