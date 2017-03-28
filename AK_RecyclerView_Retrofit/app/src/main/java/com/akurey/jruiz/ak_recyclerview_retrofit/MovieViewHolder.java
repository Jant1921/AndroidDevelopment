package com.akurey.jruiz.ak_recyclerview_retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jruiz on 3/27/2017.
 */

//Movie Holder inner class, holds references to the views in the row layout
public class MovieViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public MovieViewHolder(View itemView){
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}