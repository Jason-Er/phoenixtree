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
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.view.FragmentNavigation;
import com.example.phoenixtree.viewmodel.ParticipateViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipateFragment extends Fragment {

    final private static String TAG = ParticipateFragment.class.getName();
    private ParticipateViewModel viewModel;
    public static final String ID_KEY = "id";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    FragmentNavigation fragmentNavigation;
    @Inject
    SceneNavigation sceneNavigation;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipateViewModel.class);
        Bundle args = getArguments();
        if (args != null && args.containsKey(ID_KEY)) {
            viewModel.setPlayId(args.getLong(ID_KEY));
        } else {
            viewModel.setPlayId(0);
        }

        viewModel.play.observe(this, new Observer<Resource<StagePlay>>() {
            @Override
            public void onChanged(@Nullable Resource<StagePlay> stagePlayResource) {
                Log.i(TAG, "onAttach onChanged");
                switch (stagePlayResource.status) {
                    case SUCCESS:
                        StagePlay play = stagePlayResource.data;
                        sceneNavigation.setStageScenes(play.scenes);
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_participate, container, false);
        return root;
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

}
