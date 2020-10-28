package util;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import data.ApiClient;
import data.ApiInterface;
import model.Movie;
import model.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    Application application;
    public static ApiInterface apiInterface;
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    ArrayList<Movie> movies = new ArrayList<>();
    private static Repository repository;

    public Repository(final Application application) {
        this.application = application;
    }

    public static Repository getInstance(){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }

    public Repository() {
        apiInterface = ApiClient.getInterface();
    }

    public MutableLiveData<List<Movie>> fetchMovies(){
        Call<MovieResponse> call = apiInterface.getMovies();
        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(final Call<MovieResponse> call, final Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    MovieResponse movieResponse = response.body();
                    movies = (ArrayList<Movie>) movieResponse.getMovies();
                    mutableLiveData.setValue(movies);
            }

        }

            @Override
            public void onFailure(final Call<MovieResponse> call, final Throwable t) {
                Log.d("Fail", "onFailure: + Fail");
            }
            });
        return mutableLiveData;
    }
}


