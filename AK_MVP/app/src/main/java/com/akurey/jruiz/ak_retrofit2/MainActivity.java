package com.akurey.jruiz.ak_retrofit2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akurey.jruiz.ak_retrofit2.repos.ReposActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText userNickname;
    TextView loadedName;
    private RecyclerView mRecyclerView;
    private Repos_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Github Repos Example");
        userNickname = (EditText) findViewById(R.id.input_user_nickname);
        loadedName = (TextView) findViewById(R.id.label_loaded_name);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_repos);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        mAdapter = new Repos_Adapter(getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);

    }

    private void showToast(String pMessage){
        Toast.makeText(getApplicationContext(),pMessage,Toast.LENGTH_SHORT)
                .show();
    }

    Callback userCallback = new  Callback<Github_User>() {
        @Override
        public void onResponse(Call<Github_User> call, Response<Github_User> response) {
            int code = response.code();
            if(code == 200){
                Github_User user = response.body();
                loadedName.setText(user.name);

            }else{
                showToast("Did not work: " + String.valueOf(code));
            }
        }

        @Override
        public void onFailure(Call<Github_User> call, Throwable t) {
            showToast("Nope. "+t.getMessage());
        }
    };

    Callback repoCallback = new Callback<List<Github_Repo>>(){

        @Override
        public void onResponse(Call<List<Github_Repo>> call, Response<List<Github_Repo>> response) {
            if(response.isSuccessful()){
                List<Github_Repo> repos = response.body();
                StringBuilder builder =  new StringBuilder();
                for(Github_Repo repo: repos){
                    builder.append(repo.name + " " + repo.toString());
                }
                //showToast(builder.toString());
                mAdapter.setRepoList(repos);
            }else{
                showToast("Error Code: "+response.message());
                loadedName.setText(response.message());

            }
        }

        @Override
        public void onFailure(Call<List<Github_Repo>> call, Throwable t) {
            showToast("Did not work. "+t.getMessage());
        }
    };
    public void openFragment(View view){
        Intent mvpTest = new Intent(this, ReposActivity.class);
        startActivity(mvpTest);
    }

    public void onClick(View view){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Github_API.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Github_API githubUserAPI = retrofit.create(Github_API.class);

        /*
        case R.id.loadUserData:
            // prepare call in Retrofit2

            break;
            */
            Call<Github_User> callUser = githubUserAPI.getUser(userNickname.getText().toString());
            Call<List<Github_Repo>> callRepos = githubUserAPI.getRepos(userNickname.getText().toString());

            //asynchronous call
            callUser.enqueue(userCallback);
            callRepos.enqueue(repoCallback);
            loadedName.setText(R.string.loading_text);
            mAdapter.setRepoList(new ArrayList<Github_Repo>());

        //no-default

    }


}
