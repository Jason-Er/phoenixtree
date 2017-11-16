package com.example.phoenixtree.view.participate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.phoenixtree.R;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.view.FragmentNavigation;
import com.example.phoenixtree.viewmodel.StagePlayViewModel;

import java.util.MissingResourceException;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipateFragment extends Fragment {

    final private static String TAG = ParticipateFragment.class.getName();
    private StagePlayViewModel viewModel;
    private static final String ID_KEY = "id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    FragmentNavigation fragmentNavigation;
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

        viewModel.play.observe(this, new Observer<Resource<StagePlay>>() {
            @Override
            public void onChanged(@Nullable Resource<StagePlay> stagePlayResource) {
                Log.i(TAG, "onAttach onChanged");
                switch (stagePlayResource.status) {
                    case SUCCESS:
                        StagePlay play = stagePlayResource.data;
                        sceneNavigation.setStageScenes(play.scenes);
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
        hideSystemUI();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showSystemUI();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
