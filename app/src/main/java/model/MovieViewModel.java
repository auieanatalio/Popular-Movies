package model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import java.util.List;

import util.Repository;

public class MovieViewModel extends AndroidViewModel {

    private Repository repository;
    MutableLiveData<List<Movie>> mutableLiveData= new MutableLiveData<>();

    public MovieViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<List<Movie>> getMovieRepository(){
        repository.getInstance();
       mutableLiveData = repository.fetchMovies();
        return mutableLiveData;
    }

}
