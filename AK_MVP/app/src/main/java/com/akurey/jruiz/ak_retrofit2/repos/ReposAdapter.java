package com.akurey.jruiz.ak_retrofit2.repos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akurey.jruiz.ak_retrofit2.data.GithubRepo;
import com.akurey.jruiz.ak_retrofit2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jruiz on 4/1/2017.
 */

public class ReposAdapter extends RecyclerView.Adapter<RepoHolder> {

    private List<GithubRepo> repoList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ReposAdapter(Context pContext){
        this.mContext = pContext;
        this.mInflater = LayoutInflater.from(pContext);
        this.repoList = new ArrayList<>();
    }

    public void setRepoList(List<GithubRepo> pRepoList){
        this.repoList.clear();
        this.repoList.addAll(pRepoList);
        notifyDataSetChanged();
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.repo_data,parent,false);
        RepoHolder viewHolder = new RepoHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        GithubRepo repo = repoList.get(position);
        holder.setRepoName(repo.getName());
        holder.setRepoURL(repo.getUrl());
    }

    @Override
    public int getItemCount() {
        return (repoList == null)? 0 : repoList.size();
    }
}
