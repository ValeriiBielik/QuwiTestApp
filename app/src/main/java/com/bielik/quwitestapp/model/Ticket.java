package com.bielik.quwitestapp.model;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("id")
    private long id;
    @SerializedName("id_project")
    private long projectId;
    @SerializedName("number")
    private int number;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("logo_url")
    private String icon;
    @SerializedName("author_name")
    private String authorName;
    @SerializedName("dta_create")
    private String dateCreated;

    public long getId() {
        return id;
    }

    public long getProjectId() {
        return projectId;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public String getIcon() {
        return icon;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedFormatted() {
        return dateCreated;
    }
}
