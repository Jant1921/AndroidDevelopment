package com.akurey.jruiz.ak_retrofit2.repos;

import android.support.annotation.NonNull;
import android.util.Log;

import com.akurey.jruiz.ak_retrofit2.data.GithubAPI;
import com.akurey.jruiz.ak_retrofit2.data.GithubRepo;
import com.akurey.jruiz.ak_retrofit2.data.GithubUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by jruiz on 22-Apr-17.
 */

public class ReposPresenter implements ReposContract.Presenter {

    private final ReposContract.View mReposView;


    /**
     * Constructor
     * @param pReposView a reference to the view
     */
    public ReposPresenter(@NonNull ReposContract.View pReposView){
        mReposView = checkNotNull(pReposView);
        pReposView.setPresenter(this);
    }

    /**
     * callback to get the user's name
     */
    Callback userCallback = new  Callback<GithubUser>() {
        @Override
        public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
            int code = response.code();
            if(code == 200){
                GithubUser user = response.body();
                mReposView.displayUserName(user.getName());
            }else{
                mReposView.showResultMessage("Did not work: " + String.valueOf(code));
            }
        }

        @Override
        public void onFailure(Call<GithubUser> call, Throwable t) {
            mReposView.showResultMessage("Nope. "+t.getMessage());
        }
    };

    /**
     * Callback to get the user's repositories
     */
    Callback repoCallback = new Callback<List<GithubRepo>>(){
        @Override
        public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
            if(response.isSuccessful()){
                List<GithubRepo> repos = response.body();
                StringBuilder builder =  new StringBuilder();
                for(GithubRepo repo: repos){
                    builder.append(repo.getName() + " " + repo.toString());
                }
                mReposView.showUserRepos(repos);
            }else{
                mReposView.showResultMessage("Error Code: "+response.message());
                mReposView.displayUserName(response.message());
            }
        }

        @Override
        public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
            mReposView.showResultMessage("Did not work. "+t.getMessage());
        }
    };

    /**
     * Create the request to the Github API
     * @param pNickname the user nickname, used to get all user's data
     */
    @Override
    public void loadUserData(String pNickname) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GithubAPI githubUserAPI = retrofit.create(GithubAPI.class);

        Call<GithubUser> callUser = githubUserAPI.getUser(pNickname);
        Call<List<GithubRepo>> callRepos = githubUserAPI.getRepos(pNickname);

        //asynchronous calls
        callUser.enqueue(userCallback);
        callRepos.enqueue(repoCallback);
    }

    @Override
    public void start() {
        mReposView.showResultMessage("Presenter started");
    }


}
