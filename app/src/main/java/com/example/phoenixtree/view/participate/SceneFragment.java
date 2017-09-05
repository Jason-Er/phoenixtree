package com.example.phoenixtree.view.participate;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.util.SceneAdapter;
import com.example.phoenixtree.util.SceneLayoutManager;
import com.example.phoenixtree.view.NavigationController;
import com.example.phoenixtree.viewmodel.SceneViewModel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends LifecycleFragment {

    final private static String TAG = SceneFragment.class.getName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    NavigationController navigationController;

    private static final String UID_KEY = "uid";
    private SceneViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SceneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long sceneId = 1L; // getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(SceneViewModel.class);
        viewModel.load(sceneId);
        viewModel.getKeyframe().observe(this, keyframe -> {
            ((SceneAdapter)adapter).setKeyframe(keyframe);
            Log.i(TAG, "keyframe updated!");
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "SceneFragment onCreateView");
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView)inflater.inflate(R.layout.fragment_scene, container, false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new SceneLayoutManager();// new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SceneAdapter();
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

}
