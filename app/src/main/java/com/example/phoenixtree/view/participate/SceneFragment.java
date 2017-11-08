package com.example.phoenixtree.view.participate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.model.keyframe.Keyframe;
import com.example.phoenixtree.R;
import com.example.phoenixtree.util.sceneRecyclerView.SceneAdapter;
import com.example.phoenixtree.util.sceneRecyclerView.SceneLayoutManager;
import com.example.phoenixtree.viewmodel.SceneViewModel;
import com.example.phoenixtree.viewmodel.StagePlayViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends Fragment {

    final private static String TAG = SceneFragment.class.getName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static final String ID_KEY = "id";
    private StagePlayViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SceneFragment() {
        // Required empty public constructor
    }

    public static SceneFragment create(long stageSceneId) {
        SceneFragment fragment = new SceneFragment();
        Bundle args = new Bundle();
        args.putLong(ID_KEY, stageSceneId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(StagePlayViewModel.class);
        if (args != null && args.containsKey(ID_KEY)) {
            viewModel.setSceneId(args.getLong(ID_KEY));
        } else {
            viewModel.setSceneId(0L);
        }
        viewModel.keyframe.observe(this, new Observer<Keyframe>() {
            @Override
            public void onChanged(@Nullable Keyframe keyframe) {
                Log.i(TAG, "onActivityCreated onChanged");
                if(keyframe != null) {
                    ((SceneAdapter) adapter).setKeyframe(keyframe);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "SceneFragment onCreateView");
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView)inflater.inflate(R.layout.fragment_scene, container, false);
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

}
