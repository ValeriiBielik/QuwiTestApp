package com.bielik.quwitestapp.network.response;

import com.bielik.quwitestapp.model.Project;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectResponse {

    @SerializedName("projects")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
