package com.akurey.jruiz.ak_retrofit2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jruiz on 4/1/2017.
 */

public class RepoHolder extends RecyclerView.ViewHolder{
    private TextView repoName;
    private TextView repoURL;

    public RepoHolder(View row){
        super(row);
        repoName = (TextView) row.findViewById(R.id.repo_name);
        repoURL = (TextView) row.findViewById(R.id.repo_url);
    }

    public String getRepoName() {
        return repoName.getText().toString();
    }

    public void setRepoName(String pName) {
        this.repoName.setText(pName);
    }

    public String getRepoURL() {
        return repoURL.getText().toString();
    }

    public void setRepoURL(String pURL) {
        this.repoURL.setText(pURL);
    }
    ;
    /*

     */
}
