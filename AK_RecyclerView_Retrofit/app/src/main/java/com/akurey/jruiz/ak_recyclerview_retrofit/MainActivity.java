package com.akurey.jruiz.ak_recyclerview_retrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //vertical layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        mAdapter = new MoviesAdapter(getApplicationContext());
        //assign the adapter to the recycler view
        mRecyclerView.setAdapter(mAdapter);
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            movies.add(new Movie());
        }
        mAdapter.setmMovieList(movies);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //The rest adapter pretty much holds our configuration
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key","387dcb227aecd0d13bcaa6b0adf21d0d");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        //create a new rest service adapter
        MoviesApiService service = restAdapter.create(MoviesApiService.class);
        service.getPopularMovies(new Callback<Movie.MovieResult>() {
            //adds the retrieved data into adapter's movie list
            @Override
            public void success(Movie.MovieResult movieResult, Response response) {
                mAdapter.setmMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
