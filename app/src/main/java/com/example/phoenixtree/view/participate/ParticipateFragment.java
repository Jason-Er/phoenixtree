package com.example.phoenixtree.view.participate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.model.Play4PW;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.util.Common;
import com.example.phoenixtree.view.NavigationController;
import com.example.phoenixtree.viewmodel.ParticipateViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipateFragment extends Fragment {

    final private static String TAG = ParticipateFragment.class.getName();
    private ParticipateViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    NavigationController navigationController;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipateViewModel.class);
        viewModel.setPlayId(1L);
        viewModel.play.observe(this, new Observer<Resource<Play4PW>>() {
            @Override
            public void onChanged(@Nullable Resource<Play4PW> play4PWResource) {
                Log.i(TAG, "onAttach onChanged");
                switch (play4PWResource.status) {
                    case SUCCESS:
                        Play4PW play4PW = play4PWResource.data;
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_participate, container, false);
        SceneFragment sceneFragment = (SceneFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragment_participate_frame);
        if (sceneFragment == null) {
            // trans argument for scene fragment
            sceneFragment = new SceneFragment();
            Bundle bundle = new Bundle();
            bundle.putLong(SceneFragment.ID_KEY, 1L);
            sceneFragment.setArguments(bundle);
            Common.addFragment(R.id.fragment_participate_frame, sceneFragment, getActivity());
        }
        return root;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
