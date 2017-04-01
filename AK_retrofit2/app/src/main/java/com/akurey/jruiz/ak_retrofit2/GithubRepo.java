package com.akurey.jruiz.ak_retrofit2;

import com.google.gson.annotations.SerializedName;

public class GithubRepo {
    String name;
    @SerializedName("html_url")
    String url;

    public GithubRepo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return (name+" "+url);
    }
}
