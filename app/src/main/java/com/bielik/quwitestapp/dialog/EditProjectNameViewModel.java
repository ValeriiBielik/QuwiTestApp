package com.bielik.quwitestapp.dialog;

import android.text.TextUtils;

import com.bielik.quwitestapp.base.BaseViewModel;
import com.bielik.quwitestapp.network.ApiService;
import com.bielik.quwitestapp.network.RetrofitService;

import static java.net.HttpURLConnection.HTTP_OK;

public class EditProjectNameViewModel extends BaseViewModel<EditProjectNameDialogView> {

    private final ApiService api = RetrofitService.getApi();
    private long projectId;

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void editProjectName(CharSequence name) {
        if (TextUtils.isEmpty(name)) {
            view.onProjectNameEmpty();
            return;
        }
        api.editProjectName(projectId, name.toString()).observeForever(response -> {
            if (response.isSuccessful() && response.code == HTTP_OK) {
                if (response.body != null) {
                    view.onProjectNameEdited();
                    return;
                }
            }
            if (response.errorMessage != null) {
                view.onError(response.errorMessage);
            }
        });
    }
}
