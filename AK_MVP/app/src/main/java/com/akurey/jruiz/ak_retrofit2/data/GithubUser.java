package com.akurey.jruiz.ak_retrofit2.data;

/**
 * Created by jruiz on 3/28/2017.
 */

public class GithubUser {
    String login;
    String name;
    String email;

    @Override
    public String toString(){
        return (login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
