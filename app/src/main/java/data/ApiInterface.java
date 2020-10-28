package data;

import model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("3/movie/popular?api_key=3284ec2420e25da029b6cee9ec90d034&language=en-US&page=1")
    Call<MovieResponse> getMovies();
}
