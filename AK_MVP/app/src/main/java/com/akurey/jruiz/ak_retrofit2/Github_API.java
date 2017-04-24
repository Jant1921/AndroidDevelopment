package com.akurey.jruiz.ak_retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Github_API {
    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Call<Github_User> getUser(@Path("user") String user);

    @GET("/users/{user}/repos")
    Call<List<Github_Repo>> getRepos(@Path("user") String user);
}
