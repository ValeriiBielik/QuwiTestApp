package com.bielik.quwitestapp.screen.main;

import androidx.lifecycle.MutableLiveData;

import com.bielik.quwitestapp.base.BaseViewModel;
import com.bielik.quwitestapp.model.Project;
import com.bielik.quwitestapp.network.ApiService;
import com.bielik.quwitestapp.network.RetrofitService;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class MainViewModel extends BaseViewModel<MainActivityView> {

    private final ApiService api = RetrofitService.getApi();
    private final MutableLiveData<List<Project>> projectsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Project>> getProjectsLiveData() {
        return projectsLiveData;
    }

    public void loadProjects() {
        api.getProjects().observeForever(response -> {
            if (response.isSuccessful() && response.code == HTTP_OK) {
                if (response.body != null) {
                    projectsLiveData.setValue(response.body.getProjects());
                    return;
                }
            }
            if (response.errorMessage != null) {
                view.onError(response.errorMessage);
            }
        });
    }
}
