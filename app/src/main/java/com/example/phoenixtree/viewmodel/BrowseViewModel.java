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
import com.example.phoenixtree.util.Pageable;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by ej on 10/16/2017.
 */

public class BrowseViewModel extends ViewModel {

    private final MutableLiveData<Pageable> pageable = new MutableLiveData<>();
    public final LiveData<Resource<List<StagePlayEntity>>> stagePlayInfoLiveData;

    @Inject
    public BrowseViewModel(final StagePlayRepository repository) {
        stagePlayInfoLiveData = Transformations.switchMap(pageable, new Function<Pageable, LiveData<Resource<List<StagePlayEntity>>>>() {
            @Override
            public LiveData<Resource<List<StagePlayEntity>>> apply(Pageable input) {
                if(pageable == null) {
                    return AbsentLiveData.create();
                } else {
                    return repository.loadStagePlayInfo(input.page, input.size);
                }
            }
        });
    }

    public void setStagePage(Pageable pageable) {
        if (Objects.equals(this.pageable.getValue(), pageable)) {
            return;
        }
        this.pageable.setValue(pageable);
    }

}
