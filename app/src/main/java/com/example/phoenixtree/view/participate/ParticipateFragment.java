package com.example.phoenixtree.view.participate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.phoenixtree.model.keyframe.Keyframe;
import com.example.phoenixtree.util.UICommon;
import com.example.phoenixtree.util.commonInterface.StagePlayInfo;
import com.example.phoenixtree.util.sceneRecyclerView.SceneAdapter;
import com.example.phoenixtree.util.sceneRecyclerView.SceneLayoutManager;
import com.example.phoenixtree.view.sceneNavigation.SceneNavigation;
import com.example.phoenixtree.viewmodel.StagePlayViewModel;

import java.util.MissingResourceException;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipateFragment extends Fragment implements StagePlayInfo {

    final private static String TAG = "ParticipateFragment";

    private static final String ID_KEY = "id";

    private StagePlayViewModel viewModel;
    private StagePlay stagePlay;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    SceneNavigation sceneNavigation;

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

        viewModel.keyframe.observe(this, new Observer<Keyframe>() {
            @Override
            public void onChanged(@Nullable Keyframe keyframe) {
                Log.i(TAG, "keyframe observe onChanged");
                if(keyframe != null) {
                    ((SceneAdapter) adapter).setKeyframe(keyframe);
                }
            }
        });

        viewModel.play.observe(this, new Observer<Resource<StagePlay>>() {
            @Override
            public void onChanged(@Nullable Resource<StagePlay> stagePlayResource) {
                Log.i(TAG, "onAttach onChanged");
                switch (stagePlayResource.status) {
                    case SUCCESS:
                        stagePlay = stagePlayResource.data;
                        sceneNavigation.setStageScenes(stagePlay.scenes);
                        if(savedInstanceState == null)
                            sceneNavigation.navigateToFirst();
                        break;
                    case ERROR:

                        break;
                    case LOADING:

                        break;
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ParticipateFragment onCreateView");
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        UICommon.hideSystemUI(getActivity());
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new SceneLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SceneAdapter();
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public static ParticipateFragment create(long stagePlayId) {
        ParticipateFragment fragment = new ParticipateFragment();
        Bundle args = new Bundle();
        args.putLong(ID_KEY, stagePlayId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        UICommon.showSystemUI(getActivity());
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }


    @Override
    public long getStagePlayID() {
        return stagePlay == null? 0L : stagePlay.stageId;
    }
}
