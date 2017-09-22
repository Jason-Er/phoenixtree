package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.model.Resource;

import javax.annotation.Nullable;

/**
 * Created by ej on 9/21/2017.
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {
    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.<ResultType>loading(null));
        final LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable ResultType data) {
                result.removeSource(dbSource);
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@android.support.annotation.Nullable ResultType newData) {
                            result.setValue(Resource.success(newData));
                        }
                    });
                }
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        final LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable ResultType newData) {
                result.setValue(Resource.loading(newData));
            }
        });
        result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final ApiResponse<RequestType> response) {
                result.removeSource(apiResponse);
                result.removeSource(dbSource);
                //noinspection ConstantConditions
                if (response.isSuccessful()) {
                    appExecutors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            saveCallResult(processResponse(response));
                            appExecutors.mainThread().execute(new Runnable() {
                                                                  @Override
                                                                  public void run() {
                                                                      result.addSource(loadFromDb(), new Observer<ResultType>() {
                                                                                  @Override
                                                                                  public void onChanged(@android.support.annotation.Nullable ResultType newData) {
                                                                                      result.setValue(Resource.success(newData));
                                                                                  }
                                                                              }
                                                                      );
                                                                  }
                                                              }
                            );
                        }
                    });
                } else {
                    onFetchFailed();
                    result.addSource(dbSource, new Observer<ResultType>() {
                                @Override
                                public void onChanged(@android.support.annotation.Nullable ResultType newData) {
                                    result.setValue(Resource.error(response.errorMessage, newData));
                                }
                            }
                    );
                }
            }
        });
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

}

