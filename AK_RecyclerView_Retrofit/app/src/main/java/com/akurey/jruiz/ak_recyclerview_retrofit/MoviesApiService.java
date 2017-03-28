package com.akurey.jruiz.ak_recyclerview_retrofit;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jruiz on 3/28/2017.
 */

public interface MoviesApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<Movie.MovieResult> cb);
}
