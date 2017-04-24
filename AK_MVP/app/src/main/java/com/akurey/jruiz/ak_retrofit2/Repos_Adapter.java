package com.akurey.jruiz.ak_retrofit2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jruiz on 4/1/2017.
 */

public class Repos_Adapter extends RecyclerView.Adapter<Repo_Holder> {

    private List<Github_Repo> repoList;
    private LayoutInflater mInflater;
    private Context mContext;

    public Repos_Adapter(Context pContext){
        this.mContext = pContext;
        this.mInflater = LayoutInflater.from(pContext);
        this.repoList = new ArrayList<>();
    }

    public void setRepoList(List<Github_Repo> pRepoList){
        this.repoList.clear();
        this.repoList.addAll(pRepoList);
        notifyDataSetChanged();
    }

    @Override
    public Repo_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.repo_data,parent,false);
        Repo_Holder viewHolder = new Repo_Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Repo_Holder holder, int position) {
        Github_Repo repo = repoList.get(position);
        holder.setRepoName(repo.getName());
        holder.setRepoURL(repo.getUrl());
    }

    @Override
    public int getItemCount() {
        return (repoList == null)? 0 : repoList.size();
    }
}
