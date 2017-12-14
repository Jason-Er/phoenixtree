package com.example.phoenixtree.view.compose;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.util.UICommon;
import com.example.phoenixtree.util.commonInterface.StagePlayInfo;
import com.example.phoenixtree.util.composeRecyclerView.ComposeAdapter;
import com.example.phoenixtree.util.composeRecyclerView.ComposeLayoutManager;
import com.example.phoenixtree.viewmodel.StagePlayViewModel;

import java.util.MissingResourceException;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by ej on 11/30/2017.
 */

public class ComposeFragment extends Fragment implements StagePlayInfo {

    private static final String TAG = "ComposeFragment";
    private static final String ID_KEY = "id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private StagePlayViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private StagePlay stagePlay;

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(StagePlayViewModel.class);
        Bundle args = getArguments();
        if (args != null && args.containsKey(ID_KEY)) {
            viewModel.setPlayId( args.getLong(ID_KEY) );
        } else {
            // TODO: 11/14/2017 throw exception or show custom dialog
            throw new MissingResourceException("ParticipateFragment key: "+ ID_KEY + " should not be NULL","Bundle",ID_KEY);
        }

        viewModel.play.observe(this, new Observer<Resource<StagePlay>>() {
            @Override
            public void onChanged(@Nullable Resource<StagePlay> stagePlayResource) {
                switch (stagePlayResource.status) {
                    case SUCCESS:
                        Log.i(TAG, "play data success");
                        stagePlay = stagePlayResource.data;
                        // sceneNavigation.setStageScenes(play.scenes);
                        if(savedInstanceState == null);
                           // sceneNavigation.navigateToFirst();

                        ((ComposeAdapter)adapter).setDataset(stagePlay.scenes.get(0).stageLines);
                        break;
                    case ERROR:

                        break;
                    case LOADING:

                        break;
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "ComposeFragment onCreateView");
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        UICommon.hideSystemUI(getActivity());
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView)inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new ComposeLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ComposeAdapter();
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        UICommon.showSystemUI(getActivity());
    }

    public static ComposeFragment create(long stagePlayId) {
        ComposeFragment fragment = new ComposeFragment();
        Bundle args = new Bundle();
        args.putLong(ID_KEY, stagePlayId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public long getStagePlayID() {
        return stagePlay == null? 0L : stagePlay.stageId;
    }
}
