package com.akurey.jruiz.ak_retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {
    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> getRepos(@Path("user") String user);
}
