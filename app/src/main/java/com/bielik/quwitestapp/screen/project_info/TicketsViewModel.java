package com.bielik.quwitestapp.screen.project_info;

import androidx.lifecycle.MutableLiveData;

import com.bielik.quwitestapp.base.BaseViewModel;
import com.bielik.quwitestapp.model.Project;
import com.bielik.quwitestapp.model.Ticket;
import com.bielik.quwitestapp.network.ApiService;
import com.bielik.quwitestapp.network.RetrofitService;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class TicketsViewModel extends BaseViewModel<TicketsActivityView> {

    private final ApiService api = RetrofitService.getApi();
    private final MutableLiveData<List<Ticket>> ticketsLiveData = new MutableLiveData<>();
    private Project project;

    public void setProject(Project project) {
        this.project = project;
    }

    public MutableLiveData<List<Ticket>> getTicketsLiveData() {
        return ticketsLiveData;
    }

    public void loadTickets() {
        api.getTickets(project.getId()).observeForever(response -> {
            if (response.isSuccessful() && response.code == HTTP_OK) {
                if (response.body != null) {
                    ticketsLiveData.setValue(response.body.getTickets());
                    return;
                }
            }
            if (response.errorMessage != null) {
                view.onError(response.errorMessage);
            }
        });
    }
}
