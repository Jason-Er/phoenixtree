package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.repository.StagePlayRepository;
import com.example.phoenixtree.util.AbsentLiveData;
import com.example.phoenixtree.util.PageRequest;
import com.example.phoenixtree.util.RetrievePageInfo;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 10/16/2017.
 */
@Singleton
public class BrowseViewModel extends ViewModel {

    private final MutableLiveData<PageRequest> pageable = new MutableLiveData<>();
    public final LiveData<Resource<RetrievePageInfo<List<StagePlayEntity>>>> stagePlayInfoLiveData;

    @Inject
    public BrowseViewModel(final StagePlayRepository repository) {
        stagePlayInfoLiveData = Transformations.switchMap(pageable, new Function<PageRequest, LiveData<Resource<RetrievePageInfo<List<StagePlayEntity>>>>>() {
            @Override
            public LiveData<Resource<RetrievePageInfo<List<StagePlayEntity>>>> apply(PageRequest input) {
                if(pageable == null) {
                    return AbsentLiveData.create();
                } else {
                    return repository.loadPlayInfo(input.page, input.size);
                }
            }
        });
    }

    public void setRequestPage(PageRequest pageable) {
        if (Objects.equals(this.pageable.getValue(), pageable)) {
            return;
        }
        this.pageable.setValue(pageable);
    }

}
