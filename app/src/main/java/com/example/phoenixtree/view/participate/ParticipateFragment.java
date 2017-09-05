package com.example.phoenixtree.view.participate;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.app.PhoenixtreeApplication;
import com.example.phoenixtree.util.Common;
import com.example.phoenixtree.viewmodel.ParticipateViewModel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipateFragment extends LifecycleFragment {

    final private static String TAG = ParticipateFragment.class.getName();
    private ParticipateViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((PhoenixtreeApplication)getActivity().getApplication())
                .getAppComponent()
                .inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipateViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ParticipateFragment onCreateView");
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_participate, container, false);
        SceneFragment sceneFragment = (SceneFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragment_participate_frame);
        if (sceneFragment == null) {
            sceneFragment = new SceneFragment();
            Common.addFragment(R.id.fragment_participate_frame, sceneFragment, getActivity());
        }
        return root;
    }

}
