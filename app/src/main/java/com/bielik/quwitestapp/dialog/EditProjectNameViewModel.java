package com.bielik.quwitestapp.dialog;

import android.text.TextUtils;

import com.bielik.quwitestapp.base.BaseViewModel;
import com.bielik.quwitestapp.network.ApiService;
import com.bielik.quwitestapp.network.RetrofitService;

import static java.net.HttpURLConnection.HTTP_OK;

public class EditProjectNameViewModel extends BaseViewModel<EditProjectNameDialogView> {

    private final ApiService api = RetrofitService.getApi();
    private long projectId;
    private String currentProjectName;

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getCurrentProjectName() {
        return currentProjectName;
    }

    public void setCurrentProjectName(String currentProjectName) {
        this.currentProjectName = currentProjectName;
    }

    public void editProjectName(CharSequence name) {
        if (TextUtils.isEmpty(name)) {
            view.onProjectNameEmpty();
            return;
        } else if (name.toString().equals(currentProjectName)) {
            view.onProjectNameNotChanged();
            return;
        }
        api.editProjectName(projectId, name.toString()).observeForever(response -> {
            if (response.isSuccessful() && response.code == HTTP_OK) {
                if (response.body != null) {
                    view.onProjectNameEdited(name.toString());
                    return;
                }
            }
            if (response.errorMessage != null) {
                view.onError(response.errorMessage);
            }
        });
    }
}
