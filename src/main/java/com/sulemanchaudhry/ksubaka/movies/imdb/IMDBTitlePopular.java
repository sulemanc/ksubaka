package com.sulemanchaudhry.ksubaka.movies.imdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* Created by suleman on 06/09/2016.
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class IMDBTitlePopular {
    private String id;
    private String title;
    private String name;
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IMDBTitlePopular{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
