package com.akurey.jruiz.ak_recyclerview_retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jruiz on 3/27/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MoviesAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }

    public void setmMovieList(List<Movie> movieList){
        this.mMovieList.clear();
        this.mMovieList.addAll(movieList);
        //The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_movie,parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        Picasso.with(mContext)
                .load(movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (mMovieList == null)? 0 : mMovieList.size();
    }
}
