package com.akurey.jruiz.ak_retrofit2.repos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akurey.jruiz.ak_retrofit2.data.GithubRepo;
import com.akurey.jruiz.ak_retrofit2.R;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by jruiz on 21-Apr-17.
 */


public class ReposFragment extends Fragment implements ReposContract.View {

    private ReposContract.Presenter mPresenter;
    private EditText userNickname;
    private TextView loadedName;
    private Button btn_loadData;

    private RecyclerView mRecyclerView;
    private ReposAdapter mAdapter;

    public static ReposFragment newInstance() {
        return new ReposFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.repos_frag,container,false);
        userNickname = (EditText) root.findViewById(R.id.input_user_nickname);
        loadedName = (TextView) root.findViewById(R.id.label_loaded_name);
        btn_loadData = (Button) root.findViewById(R.id.btn_loadUserData);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView_repos);
        mRecyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),1));

        mAdapter = new ReposAdapter(root.getContext());

        mRecyclerView.setAdapter(mAdapter);

        btn_loadData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                askForUserData();
            }
        });


        return root;
    }

    /**
     * saves a local reference to the presenter
     * @param presenter a presenter reference
     */
    @Override
    public void setPresenter(ReposContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    /**
     * updates the user's name displayed
     * @param pUserName
     */
    @Override
    public void displayUserName(String pUserName) {
        loadedName.setText(pUserName);
    }

    /**
     * Set the list of repos to be shown
     * @param pRepos a list of repositories
     */
    @Override
    public void showUserRepos(List<GithubRepo> pRepos) {
        mAdapter.setRepoList(pRepos);
    }

    /**
     * Show a toast message
     * @param pMessage message to show
     */
    @Override
    public void showResultMessage(String pMessage) {
        Toast.makeText(getActivity(),pMessage,Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * Ask to the presenter for the user data
     */
    @Override
    public void askForUserData() {
        loadedName.setText(R.string.loading_text);
        mAdapter.setRepoList(new ArrayList<GithubRepo>());
        mPresenter.loadUserData(userNickname.getText().toString());
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }


}
