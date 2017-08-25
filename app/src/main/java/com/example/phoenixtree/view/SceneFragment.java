package com.example.phoenixtree.view;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.R;
import com.example.phoenixtree.util.Fake;
import com.example.phoenixtree.util.SceneAdapter;
import com.example.phoenixtree.util.SceneLayoutManager;
import com.example.phoenixtree.viewmodel.SceneViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends LifecycleFragment {

    final private static String TAG = SceneFragment.class.getName();

    private static final String UID_KEY = "uid";
    private SceneViewModel sceneViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SceneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String userId = "1"; // getArguments().getString(UID_KEY);
        sceneViewModel = ViewModelProviders.of(this).get(SceneViewModel.class);
        sceneViewModel.load(userId);
        sceneViewModel.getKeyframe().observe(this, keyframe -> {
            // update UI
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
        Keyframe keyframe = Fake.propagateKeyframe(); // TODO: 8/23/2017 for test only must be removed later
        adapter = new SceneAdapter(keyframe);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

}
