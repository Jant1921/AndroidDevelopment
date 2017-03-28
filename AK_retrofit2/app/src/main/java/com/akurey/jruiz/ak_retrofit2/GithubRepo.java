package com.akurey.jruiz.ak_retrofit2;

public class GithubRepo {
    String name;
    String url;

    @Override
    public String toString(){
        return (name+" "+url);
    }
}
