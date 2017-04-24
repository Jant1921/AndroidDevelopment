package com.akurey.jruiz.ak_retrofit2.repos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akurey.jruiz.ak_retrofit2.R;
import com.akurey.jruiz.ak_retrofit2.util.ActivityUtils;

public class ReposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repos_act);


        ReposFragment reposFragment = (ReposFragment) getFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (reposFragment == null) {
            reposFragment = ReposFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    reposFragment, R.id.contentFrame);
        }

        new ReposPresenter(reposFragment);
    }
}
