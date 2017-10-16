package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.repository.StageRepository;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by ej on 10/16/2017.
 */

public class BrowseViewModel extends ViewModel {

    private final MutableLiveData<Long> stagePage = new MutableLiveData<>();
    public final LiveData<Resource<List<StagePlayEntity>>> stageEntityListLiveData;

    @Inject
    public BrowseViewModel(final StageRepository repository) {
        stageEntityListLiveData = Transformations.switchMap(stagePage, new Function<Long, LiveData<Resource<List<StagePlayEntity>>>>() {
            @Override
            public LiveData<Resource<List<StagePlayEntity>>> apply(Long input) {
                return null;
            }
        });
    }

    public void setStagePage(long stagePage) {
        if (Objects.equals(this.stagePage.getValue(), stagePage)) {
            return;
        }
        this.stagePage.setValue(stagePage);
    }

}
