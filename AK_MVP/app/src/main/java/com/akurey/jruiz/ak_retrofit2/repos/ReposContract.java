package com.akurey.jruiz.ak_retrofit2.repos;

import com.akurey.jruiz.ak_retrofit2.BasePresenter;
import com.akurey.jruiz.ak_retrofit2.BaseView;
import com.akurey.jruiz.ak_retrofit2.data.GithubRepo;

import java.util.List;

/**
 * Created by jruiz on 21-Apr-17.
 */

public interface ReposContract {
    interface View extends BaseView<Presenter> {

        void displayUserName(String pUserName);
        void showUserRepos(List<GithubRepo> pRepos);
        void showResultMessage(String pMessage);
        //onClick action
        void askForUserData();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadUserData(String pNickName);

    }

}
