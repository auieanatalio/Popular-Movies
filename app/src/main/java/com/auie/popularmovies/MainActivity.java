package com.auie.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import data.ApiClient;
import data.ApiInterface;
import model.Movie;
import model.MovieResponse;
import model.MovieViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.RecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Movie> movies;
    RecyclerAdapter adapter;
    MovieViewModel movieViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        List<Movie> movieList = new ArrayList<>();
        adapter = new RecyclerAdapter(this, movieList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovieRepository().observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        adapter.setMovieList(movies);

                    }
                });

        setRecyclerViewData();
    }

    public void setRecyclerViewData() {
        adapter = new RecyclerAdapter(this, movies);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}