package com.bielik.quwitestapp.model;

import com.google.gson.annotations.SerializedName;

public class Project {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("logo_url")
    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
