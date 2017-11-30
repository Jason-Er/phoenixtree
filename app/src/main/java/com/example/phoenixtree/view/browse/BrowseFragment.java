package com.example.phoenixtree.view.browse;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.util.PageRequest;
import com.example.phoenixtree.util.RetrievePageInfo;
import com.example.phoenixtree.util.browseRecyclerView.BrowseAdapter;
import com.example.phoenixtree.util.callbackInterface.OnClickCallBack;
import com.example.phoenixtree.view.main.MainActivity;
import com.example.phoenixtree.viewmodel.BrowseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by ej on 10/16/2017.
 */

public class BrowseFragment extends Fragment {

    final String TAG = "BrowseFragment";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private BrowseViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BrowseViewModel.class);
        viewModel.setRequestPage(new PageRequest(0,15,""));
        viewModel.stagePlayInfoLiveData.observe(this, new Observer<Resource<RetrievePageInfo<List<StagePlayEntity>>>>() {
            @Override
            public void onChanged(@Nullable Resource<RetrievePageInfo<List<StagePlayEntity>>> resource) {
                Log.i(TAG,"onChanged");
                switch (resource.status) {
                    case SUCCESS:
                        Log.i(TAG, "SUCCESS");
                        RetrievePageInfo<List<StagePlayEntity>> pageInfo = resource.data;
                        if(resource.data != null) {
                            ((BrowseAdapter)adapter).setDataset(resource.data.content);
                        }

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
        Log.i(TAG, "onCreateView");
        View root =  inflater.inflate(R.layout.fragment_browse, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.fragment_browse_recycler);
        int spanCount = 2;
        layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BrowseAdapter(new OnClickCallBack() {
            @Override
            public void onClick(long id) {
                ((MainActivity)getActivity()).navigateToParticipate(id);
            }
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
